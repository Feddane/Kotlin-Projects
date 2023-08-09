package com.example.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView



class QuizQuestionsActivity : AppCompatActivity() {

    private var progressBar : ProgressBar?= null
    private var tvprogress : TextView?= null
    private var tvQuestion : TextView?= null
    private var ivImage : ImageView?= null


    private var tvOptionOne : TextView?= null
    private var tvOptionTwo : TextView?= null
    private var tvOptionThree : TextView?= null
    private var tvOptionFour : TextView?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBarr)
        tvprogress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        val questionsList = Constants.getQuestions()

        for (i in questionsList){
            Log.e("Questions", i.question)
        }

        var currentPosition = 1
        val question : Question = questionsList[currentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = currentPosition
        tvprogress?.text = "$currentPosition / ${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour


    }
}