package com.example.dadjokes2.data

import com.example.dadjokes2.core.RetrofitHelper
import com.example.dadjokes2.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getJoke(): MutableList<Joke?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(DadJokesAPI::class.java).getJoke()
            mutableListOf(response.body())
        }
    }
}