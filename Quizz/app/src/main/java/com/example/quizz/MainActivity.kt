package com.example.quizz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.quizz.R.id.btn_start
//import com.example.quizz.R.id.et_name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

//        val btn = findViewById<Button>(btn_start)
//        val et_name = findViewById<EditText>(et_name)
//
//        btn.setOnClickListener{
//
//            if (et_name.text.isEmpty()){
//                Toast.makeText(this, "Please enter your name !", Toast.LENGTH_SHORT).show()
//            }else{
//                val intent = Intent(this, QuizQuestionsActivity::class.java)
//                startActivity(intent)
//            }
//        }

}
}

