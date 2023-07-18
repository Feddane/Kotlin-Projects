package com.example.kotlinfundamentals

import android.os.Bundle
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
import com.example.kotlinfundamentals.ui.theme.KotlinFundamentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val click = findViewById<Button>(R.id.btn)
        val text = findViewById<TextView>(R.id.text)
        var timeClicked = 0

        click.setOnClickListener{

            timeClicked = timeClicked + 1
            text.text = timeClicked.toString()

            Toast.makeText(this, "hi it's meee", Toast.LENGTH_SHORT).show()
        }



    }
}

