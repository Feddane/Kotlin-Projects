package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dobcalc.ui.theme.DOBcalcTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.button)

        btn.setOnClickListener{
            clickDatePicker()
        }

    }

    fun clickDatePicker (){

        val mycalendar = Calendar.getInstance()
        val year =  mycalendar.get(Calendar.YEAR)
        val month =  mycalendar.get(Calendar.MONTH)
        val day =  mycalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            },
            year,
            month,
            day
        ).show()
        }




    }
