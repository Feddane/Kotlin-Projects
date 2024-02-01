package com.example.quizonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizonline.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}