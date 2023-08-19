package com.example.api_demo

import android.app.Dialog
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.api_demo.ui.theme.API_DEMOTheme
import com.example.api_demo.ui.theme.ResponseData
import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callAPILoginAsyncTask("chaima", "123456789").execute()
    }

    private inner class callAPILoginAsyncTask(val username : String, val password: String): AsyncTask<Any, Void, String>() {

        private var customProgressDialog: Dialog? = null

        override fun onPreExecute() {
            super.onPreExecute()
            showProgressDialog()
        }
        override fun doInBackground(vararg params: Any?): String {
            var result : String
            var connection : HttpURLConnection? = null

            try {
                val url = URL("http://www.mocky.io/v2/5e3826143100006a00d37ffa")
                connection = url.openConnection() as HttpURLConnection
                connection.doInput = true //to get Data
                connection.doOutput = true //to send Data

                //sending post request
                connection.instanceFollowRedirects = false

                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("charset", "utf-8")
                connection.setRequestProperty("Accept", "application/json")

                connection.useCaches = false // cela signifie que la connexion réseau ne doit pas utiliser la mise en cache pour stocker temporairement les données qu'elle récupère à partir de l'URL spécifiée.

                val writeDataOutputStream = DataOutputStream(connection.outputStream)
                val jsonRequest = JSONObject()
                jsonRequest.put("username", username)
                jsonRequest.put("password", password)

                writeDataOutputStream.writeBytes(jsonRequest.toString())
                writeDataOutputStream.flush()
                writeDataOutputStream.close()





                //how to receive data
                val httpResult : Int = connection.responseCode

                if (httpResult == HttpURLConnection.HTTP_OK){
                    //read data from web
                    val inputStream = connection.inputStream

                    val reader = BufferedReader(InputStreamReader(inputStream))

                    val stringBuilder = StringBuilder()
                    var line : String?
                    try {
                    while (reader.readLine().also { line = it } != null){
                        stringBuilder.append(line + "\n")
                    }
                    }catch (e : IOException){
                        e.printStackTrace()
                    }finally {
                        try {
                            inputStream.close()
                        }catch (e: IOException){
                            e.printStackTrace()
                        }
                    }
                    result = stringBuilder.toString() //the result is the whole text : id = 1 and name = chaima
                }else{
                    result = connection.responseMessage //example: bad request or not able connecting
                }
            }catch (e : SocketTimeoutException){
                result = "Connection Timeout"
            }catch (e: Exception){
                result = "Error : " + e.message
            }finally {
                connection?.disconnect()
            }
                return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            cancelProgressDialog()

            if (result != null) {
                Log.i("JSON RESPONSE RESULT",  result)
            }

            /***************************USE    GSON            --> get values facilement********************/
            val responseData = Gson().fromJson(result, ResponseData::class.java)
            Log.i("Message", responseData.message)
            Log.i("User Id", "${responseData.user_id}")
            Log.i("Name", responseData.name)
            Log.i("Email", responseData.email)
            Log.i("Mobile", "${responseData.mobile}")

            // Profile Details
            Log.i("Is Profile Completed", "${responseData.profile_details.is_profile_completed}")
            Log.i("Rating", "${responseData.profile_details.rating}")

            // Data List Details.
            Log.i("Data List Size", "${responseData.data_list.size}")

            for (item in responseData.data_list.indices) {
                Log.i("Value $item", "${responseData.data_list[item]}")

                Log.i("ID", "${responseData.data_list[item].id}")
                Log.i("Value", "${responseData.data_list[item].value}")
            }

            Toast.makeText(this@MainActivity, responseData.message, Toast.LENGTH_SHORT).show()



        //use json object to read data
            val jsonObject = JSONObject(result)

            //get the values directly
            val message = jsonObject.optString("message") //get the message from the json object if it exists
            Log.i("Message", message) //in this case its returns "chaima"
            val id = jsonObject.optInt("id")
            Log.i("Id", "$id") //there's two ways: first way you call id direct et second tu rajoute $ mais c le meme resultat ca donne la valeur direct

            //what if we have inside a json another object of json?
            //This is how to read an object inside a json
            val profileDetailsObject = jsonObject.optJSONObject("profile_details")
            val isProfileCompleted = profileDetailsObject.optBoolean("is_profile_completed")
            Log.i("is profile completed ", "$isProfileCompleted")


            //And now read a list in a json object
            val dataListArray = jsonObject.optJSONArray("data_list")
            Log.i("Data List Size", "${dataListArray.length()}") // for the length of the arraylist

                //to get all the items
            for (item in 0 until dataListArray.length()){
                Log.i("value $item", "${dataListArray[item]}")

                val dataItemObject : JSONObject = dataListArray[item] as JSONObject

                val id = dataItemObject.optInt("id")
                Log.i("id", "$id")

                val value = dataItemObject.optString("value")
                Log.i("value", value)
            }

        }

        private fun showProgressDialog() {
            val customProgressDialog = Dialog(this@MainActivity)
            customProgressDialog.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog.show()
        }

        //cancel progress dialog
        private  fun cancelProgressDialog(){
                customProgressDialog?.dismiss()
        }
    }
}