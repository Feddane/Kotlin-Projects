package com.example.quizonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.quizonline.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    companion object{
        var questionModelList : List<QuestionModel> = listOf()
    }


    lateinit var binding: ActivityQuizBinding

    var currentQuestionIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestions()
    }

    private fun loadQuestions(){
        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1} / ${questionModelList.size}"
            questionProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }

}