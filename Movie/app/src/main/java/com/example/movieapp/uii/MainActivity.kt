package com.example.movieapp.uii

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.api.ApiClient
import com.example.movieapp.api.ApiService
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.response.MoviesListResponse
import com.example.movieapp.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieAdapter by lazy { MovieAdapter() }

    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Call your API
        binding.apply {
            prgBarMovies.visibility = View.VISIBLE

            val callMovieApi = api.getPopularMovies(API_KEY, 1)
            callMovieApi.enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(
                    call: Call<MoviesListResponse>,
                    response: Response<MoviesListResponse>
                ) {
                    prgBarMovies.visibility = View.GONE
                    when (response.code()) {
                        // Handle response codes as you did before
                        /*show each response what is for*/

                        //Successful responses
                        in 200..299->{
                            Log.d("Response Code", " success messages : ${response.code()}")
                            response.body().let { itBody->
                                itBody?.results.let { itData->
                                    if (itData!!.isNotEmpty()){
                                        movieAdapter.differ.submitList(itData)
                                        //recyclerview
                                        rlMovies.apply {
                                            layoutManager = LinearLayoutManager(this@MainActivity)
                                            adapter = movieAdapter
                                        }
                                    }

                                }

                            }
                        }
                        //Redirection messages
                        in 300..399->{
                            Log.d("Response Code", " Redirection messages : ${response.code()}")
                        }
                        //Client error responses
                        in 400..499->{
                            Log.d("Response Code", " Client error responses : ${response.code()}")
                        }
                        //Server error responses
                        in 500..599->{
                            Log.d("Response Code", " Server error responses : ${response.code()}")
                        }

                    }


                    movieAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    prgBarMovies.visibility = View.GONE
                    Log.e("onFailure", "Err : ${t.message}")
                }
            })
        }
    }
}
