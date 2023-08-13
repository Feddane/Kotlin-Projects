package com.example.drawing

import android.os.Bundle
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    private  var drawingView: DrawingView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawingView)
        drawingView?.setSizeForBrush(5.toFloat())

    }
}
