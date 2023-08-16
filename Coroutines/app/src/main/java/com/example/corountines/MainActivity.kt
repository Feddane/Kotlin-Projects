package com.example.corountines

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

//    var customProgressDialog : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExecute : Button = findViewById(R.id.btn_execute)

        btnExecute.setOnClickListener {
//            showProgressDialog()
           lifecycleScope.launch {
               execute("Task executed successfully")
           }
        }

    }

    private suspend fun execute(result: String) {
        withContext(Dispatchers.IO) {
            for (i in 1..30000) {
                Log.e("delay", "" + i)
            }
            runOnUiThread{
//                cancelProgressDialog()
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
            }
        }
    }



    //when you want to run something but you want the user wait for a response
//    private fun showProgressDialog() {
//        val customProgressDialog = Dialog(this)
//
//        customProgressDialog.setContentView(R.layout.dialog_custom_progress)
//
//        customProgressDialog.show()
//    }


//    private fun cancelProgressDialog(){
//        if (customProgressDialog != null){
//            customProgressDialog?.dismiss()
//            customProgressDialog = null
//        }
//    }


}