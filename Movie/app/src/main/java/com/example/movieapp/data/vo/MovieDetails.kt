package com.example.movieapp.data.vo

data class MovieDetails(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)