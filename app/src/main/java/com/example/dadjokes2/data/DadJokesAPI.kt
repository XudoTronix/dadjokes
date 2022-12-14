package com.example.dadjokes2.data

import com.example.dadjokes2.model.Joke
import com.example.dadjokes2.model.SearchJokes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DadJokesAPI {

    @Headers("Accept: application/json")
    @GET("/search")
    fun getSearchJokes(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("term") term: String
    ): Call<SearchJokes>

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getJoke(): Response<Joke>
}