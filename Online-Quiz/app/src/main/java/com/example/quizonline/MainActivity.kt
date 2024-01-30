package com.example.quizonline

import android.media.MediaCodec.LinearBlock
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var quizModelList : MutableList<QuizModel>
    lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()

    }

    private fun getDataFromFirebase(){
        //dummy data
        quizModelList.add(QuizModel("1","Programming", "All the basic programming ", "10"))
        quizModelList.add(QuizModel("2","Computer", "All the computer questions ", "20"))
        quizModelList.add(QuizModel("3","Geography", "Boost your geographic knowledge", "15"))
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = QuizAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}