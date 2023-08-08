package com.example.quizz

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.quizz.R.id.btn_start

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val btn = findViewById<Button>(btn_start)
}
}

