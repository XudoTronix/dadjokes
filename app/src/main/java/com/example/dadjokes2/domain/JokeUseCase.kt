package com.example.dadjokes2.domain

import com.example.dadjokes2.data.JokeRepository
import com.example.dadjokes2.model.Joke

class JokeUseCase {

    private val repository = JokeRepository()

    suspend operator fun invoke() = repository.getJoke()

}