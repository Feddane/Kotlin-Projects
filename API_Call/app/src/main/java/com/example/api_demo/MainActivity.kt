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
                val url = URL("https://run.mocky.io/v3/9cfaa375-0f21-48eb-b4c1-6af128b0dd77")
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