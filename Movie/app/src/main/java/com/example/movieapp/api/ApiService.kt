package com.example.movieapp.api

import com.example.movieapp.response.DetailsMovieResponse
import com.example.movieapp.response.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //method to get data
    @GET("/movie/popular")
    fun getPopularMovie(@Query("page") page: Int) : Call<MoviesListResponse>

    @GET("movie/{movie_id")
    fun getMoviedetails(@Path("movie_id") id: Int) : Call<DetailsMovieResponse>

}