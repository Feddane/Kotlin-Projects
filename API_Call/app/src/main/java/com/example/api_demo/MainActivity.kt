package com.example.api_demo

import android.app.Dialog
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
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
import org.json.JSONObject
import java.io.BufferedReader
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

        callAPILoginAsyncTask().execute()
    }

    private inner class callAPILoginAsyncTask(): AsyncTask<Any, Void, String>() {

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

            //use json object to read data
            val jsonObject = JSONObject(result)

            //get the values directly
            val name = jsonObject.optString("name") //get the name from the json object if it exists
            Log.i("Name", name) //in this case its returns "chaima"
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