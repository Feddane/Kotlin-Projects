package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.SimpleTimeZone

class MainActivity : ComponentActivity() {

    private var tvSelectedDate : TextView? = null //nullable
    private var tvAgeInMinutes : TextView? = null //nullable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.button)
        tvSelectedDate  = findViewById(R.id.tv)
        tvAgeInMinutes = findViewById(R.id.tvAge)

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
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                //select the date
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/${selectedYear}"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                //selected date in minutes
                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        ).show()
        }




    }
