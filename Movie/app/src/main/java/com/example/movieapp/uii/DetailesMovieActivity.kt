package com.example.movieapp.uii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import coil.load
import com.example.movieapp.R
import com.example.movieapp.api.ApiClient
import com.example.movieapp.api.ApiService
import com.example.movieapp.databinding.ActivityDetailesMovieBinding
import com.example.movieapp.response.DetailsMovieResponse
import com.example.movieapp.utils.Constants.POSTER_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailesMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailesMovieBinding
    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailesMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data from the adapter with extra
        val movieId = intent.getIntExtra("id", 1)

        binding.apply {
            val callMovieDetails = api.getMovieDetails(movieId, "d57d5a47b42ee06864fdde6d3c5252a9")
            callMovieDetails.enqueue(object : Callback<DetailsMovieResponse> {
                override fun onResponse(
                    call: Call<DetailsMovieResponse>,
                    response: Response<DetailsMovieResponse>
                ) {
                    Log.e("onFailure", "Err : ${response.code()}")
                    prgBarMovies.visibility = View.GONE
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let { itBody ->
                                val moviePosterURL = POSTER_BASE_URL + itBody.posterPath
                                imgMovie.load(moviePosterURL) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                }
                                imgMovieBack.load(moviePosterURL) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                }
                                tvMovieTitle.text = itBody.title
                                tvMovieTagLine.text = itBody.tagline
                                tvMovieDateRelease.text = itBody.releaseDate
                                tvMovieRating.text = itBody.voteAverage.toString()
                                tvMovieRuntime.text = itBody.runtime.toString()
                                tvMovieBudget.text = itBody.budget.toString()
                                tvMovieRevenue.text = itBody.revenue.toString()
                                tvMovieOverview.text = itBody.overview
                            }
                        }

                        in 300..399 -> {
                            Log.d("Response Code", " Redirection messages : ${response.code()}")
                        }
                        in 400..499 -> {
                            Log.d("Response Code", " Client error responses : ${response.code()}")
                        }
                        in 500..599 -> {
                            Log.d("Response Code", " Server error responses : ${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<DetailsMovieResponse>, t: Throwable) {
                    prgBarMovies.visibility = View.GONE
                    Log.e("onFailure", "Err : ${t.message}")
                }
            })
        }
    }
}
