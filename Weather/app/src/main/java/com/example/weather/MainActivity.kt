package com.example.weather

import com.google.android.gms.location.LocationRequest
import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.gms.location.*
import androidx.annotation.RequiresApi
import com.example.weather.ui.theme.Constants
import com.example.weather.ui.theme.models.WeatherResponse
import com.example.weather.ui.theme.network.WeatherService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


class MainActivity : ComponentActivity() {

    // A fused location client variable which is further used to get the user's current location
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private var mProgressDialog: Dialog? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!isLocationEnabled()){
            Toast.makeText(this, "Your location provider is turned off. Please turn it on.", Toast.LENGTH_SHORT).show()

            //go to the settings to activate location
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }else{
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    @RequiresApi(Build.VERSION_CODES.S)
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report!!.areAllPermissionsGranted()) {
                            // TODO (STEP 7: Call the location request function here.)

                            requestLocationData()
                        }

                        if (report.isAnyPermissionPermanentlyDenied) {
                            Toast.makeText(
                                this@MainActivity,
                                "You have denied location permission. Please allow it is mandatory.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }
                }).onSameThread()
                .check()

        }
        }

    /**
     * A function to request the current location. Using the fused location provider client.
     */
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    private fun requestLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    /**
     * A function used to show the alert dialog when the permissions are denied and need to allow it from settings app info.
     */
    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog,
                                           _ ->
                dialog.dismiss()
            }.show()
    }


    /* A location callback object of fused location provider client where we will get the current location details.
    */
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation
            val latitude = mLastLocation?.latitude
            Log.i("Current Latitude", "$latitude")

            val longitude = mLastLocation?.longitude
            Log.i("Current Longitude", "$longitude")
            if (latitude != null) {
                if (longitude != null) {
                    getLocationWeatherDetails(latitude, longitude)
                }
            }
        }
    }

    /**
     * A function which is used to verify that the location or GPS is enable or not of the user's device.
     */
    private fun isLocationEnabled() : Boolean{

        //This provides access to the system location services
        val locationManager : LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }


    private fun getLocationWeatherDetails(latitude: Double, longitude: Double){
        if (Constants.isNetworkAvailable(this)){

            //we get the url
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //we prepare the service
            val service : WeatherService = retrofit
                .create<WeatherService>(WeatherService::class.java)

            //we make a call with the service
            val listCall : Call<WeatherResponse> = service.getWeather(
                latitude, longitude, Constants.METRIC_UNIT, Constants.APP_ID
            )

            showCustomProgressDialog()

            listCall.enqueue(object : Callback<WeatherResponse> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<WeatherResponse>?,
                    response: Response<WeatherResponse>?
                ) {
                  if (response!!.isSuccessful){

                      hideProgressDialog()

                      val weatherList : WeatherResponse = response.body()
                      setupUI(weatherList)
                      Log.i("Response Result", "$weatherList")
                  }else{
                      val rc = response.code()
                      when(rc){
                          400 -> {
                              Log.e("Error 400",  "Bad connection")
                          }
                          404 -> {
                              Log.e("Error 404",  "Not Found")
                          }else -> {
                          Log.e("Error ",  "Generic Error")
                          }
                      }
                  }
                }

                override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                    Log.e("Erroooorrrr",  t!!.message.toString())
                    hideProgressDialog()
                }
            })

        }else{
            Toast.makeText(this, "No Internet connection available", Toast.LENGTH_SHORT).show()
        }
    }

    // TODO (STEP 5: Create a functions for SHOW and HIDE progress dialog.)
    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(this)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)

        //Start the dialog and display it on screen.
        mProgressDialog!!.show()
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    private fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupUI(weatherList: WeatherResponse) {

        // For loop to get the required data. And all are populated in the UI.
        for (z in weatherList.weather.indices) {
            Log.i("NAMEEEEEEEE", weatherList.weather[z].main)

            val tv_main : TextView = findViewById(R.id.tv_main)
            val tv_main_description : TextView = findViewById(R.id.tv_main)
            val tv_temp : TextView = findViewById(R.id.tv_temp)
            val tv_humidity : TextView = findViewById(R.id.tv_humidity)
            val tv_min : TextView = findViewById(R.id.tv_min)
            val tv_max : TextView = findViewById(R.id.tv_max)
            val tv_speed : TextView = findViewById(R.id.tv_speed)
            val tv_name : TextView = findViewById(R.id.tv_name)
            val tv_country : TextView = findViewById(R.id.tv_country)
            val tv_sunrise_time : TextView = findViewById(R.id.tv_sunrise_time)
            val tv_sunset_time : TextView = findViewById(R.id.tv_sunset_time)




            tv_main.text = weatherList.weather[z].main
            tv_main_description.text = weatherList.weather[z].description
            tv_temp.text =
                weatherList.main.temp.toString() + getUnit(application.resources.configuration.locales.toString())
            tv_humidity.text = weatherList.main.humidity.toString() + " per cent"
            tv_min.text = weatherList.main.temp_min.toString() + " min"
            tv_max.text = weatherList.main.temp_max.toString() + " max"
            tv_speed.text = weatherList.wind.speed.toString()
            tv_name.text = weatherList.name
            tv_country.text = weatherList.sys.country
            tv_sunrise_time.text = unixTime(weatherList.sys.sunrise.toLong())
            tv_sunset_time.text = unixTime(weatherList.sys.sunset.toLong())

            // Here we update the main icon
//            when (weatherList.weather[z].icon) {
//                "01d" -> iv_main.setImageResource(R.drawable.sunny)
//                "02d" -> iv_main.setImageResource(R.drawable.cloud)
//                "03d" -> iv_main.setImageResource(R.drawable.cloud)
//                "04d" -> iv_main.setImageResource(R.drawable.cloud)
//                "04n" -> iv_main.setImageResource(R.drawable.cloud)
//                "10d" -> iv_main.setImageResource(R.drawable.rain)
//                "11d" -> iv_main.setImageResource(R.drawable.storm)
//                "13d" -> iv_main.setImageResource(R.drawable.snowflake)
//                "01n" -> iv_main.setImageResource(R.drawable.cloud)
//                "02n" -> iv_main.setImageResource(R.drawable.cloud)
//                "03n" -> iv_main.setImageResource(R.drawable.cloud)
//                "10n" -> iv_main.setImageResource(R.drawable.cloud)
//                "11n" -> iv_main.setImageResource(R.drawable.rain)
//                "13n" -> iv_main.setImageResource(R.drawable.snowflake)
//            }
        }


    }



    /**
     * Function is used to get the temperature unit value.
     */
    private fun getUnit(value: String): String? {
        Log.i("unitttttt", value)
        var value = "°C"
        if ("US" == value || "LR" == value || "MM" == value) {
            value = "°F"
        }
        return value
    }

    /**
     * The function is used to get the formatted time based on the Format and the LOCALE we pass to it.
     */
    private fun unixTime(timex: Long): String? {
        val date = Date(timex * 1000L)
        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("HH:mm:ss")
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }




}