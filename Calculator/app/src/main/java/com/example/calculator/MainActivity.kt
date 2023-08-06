package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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
import com.example.calculator.ui.theme.CalculatorTheme
import java.lang.ArithmeticException

class MainActivity : ComponentActivity() {
    lateinit var tvInput: TextView
    var lastNumeric = false
    var lastDot = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit (view: View){
        //to test if the buttons are running
        tvInput.append((view as Button).text)
        lastNumeric = true

        if (tvInput.text.contains("1")){

        }
    }

    fun onClear (view: View){
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view:View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
                tvInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
    }

    private fun isOperatorAdded(value: String):Boolean {
         return if (value.startsWith("-")){
             false
         }else{
             value.contains("/") ||  value.contains("+") ||  value.contains("*") ||  value.contains("-")
         }
    }

    fun onEqual(view: View){
        if (lastNumeric ){
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }


                if (tvValue.contains("-")){
                    val splitvalue = tvValue.split("-")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }

            }catch (e: ArithmeticException){

            }
        }
    }


}