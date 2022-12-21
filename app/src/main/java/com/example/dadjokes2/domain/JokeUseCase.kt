package com.example.dadjokes2.domain

import com.example.dadjokes2.data.JokeRepository

class JokeUseCase {

    private val repository = JokeRepository()

    suspend operator fun invoke() = repository.getJoke()

}