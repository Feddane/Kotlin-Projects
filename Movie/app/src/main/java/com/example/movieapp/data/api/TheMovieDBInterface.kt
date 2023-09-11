package com.example.movieapp.data.api

import com.example.movieapp.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBInterface {

    //get the data from movieDB api
    @GET("movie/{movie_id}")
    fun getMovieDetails(
       @Path("movie_id")
       id: Int
    ): Single<MovieDetails>



}