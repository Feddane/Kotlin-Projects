package com.example.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagebutton : ImageButton =  findViewById(R.id.imageButton)
        val button1 : Button =  findViewById(R.id.button1)
        val button2 : Button =  findViewById(R.id.button2)
        val button3 : Button =  findViewById(R.id.button3)



        imagebutton.setOnClickListener {view ->
            Toast.makeText(this, "imaged touched!", Toast.LENGTH_SHORT).show()
        }

        button1.setOnClickListener {view ->
            alertDialogFunction()
        }

        button2.setOnClickListener {view ->
            customDialogFunction()
        }

        button3.setOnClickListener { view ->
            customProgressDialogFunction()
        }


    }

    private fun customProgressDialogFunction() {
        val customProgressDialog = Dialog(this)

        customProgressDialog.setContentView(R.layout.dialog_custom_progress)

        customProgressDialog.show()
    }

    private fun customDialogFunction() {
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.custom_dialog)
        val tvSubmit = customDialog.findViewById<TextView>(R.id.tv_submit)
        tvSubmit.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "clicked submit", Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        })
        val tvCancel = customDialog.findViewById<TextView>(R.id.tv_cancel)
        tvCancel.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "clicked cancel", Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        })

        customDialog.show()
    }

    private fun alertDialogFunction() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is Alert Dialog. Which is used to show alert")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){
            dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        builder.setNeutralButton("cancel"){
                dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked cancel\n operation cancel", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("No"){
                dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false) //when i click on other area the box will not disappear
        alertDialog.show()
    }
}
