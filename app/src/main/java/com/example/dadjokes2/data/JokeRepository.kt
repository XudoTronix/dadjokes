package com.example.dadjokes2.data

import com.example.dadjokes2.model.Joke
import com.example.dadjokes2.model.JokeProvider

class JokeRepository {

    private val api = JokeService()

    suspend fun getJoke(): MutableList<Joke?> {
        val response = api.getJoke()
        JokeProvider.jokes = response
        return response
    }
}