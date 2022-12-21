package com.example.dadjokes2.model


data class SearchJokes (
    val current_page: Int,
    val limit: Int,
    val next_page: Int,
    val previous_page: Int,
    val results: ArrayList<Joke>,
    val search_term: String,
    val satatus: Int,
    val total_jokes: Int,
    val total_pages: Int
)