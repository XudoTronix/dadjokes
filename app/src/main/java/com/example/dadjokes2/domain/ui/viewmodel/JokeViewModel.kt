package com.example.dadjokes2.domain.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dadjokes2.domain.JokeUseCase
import com.example.dadjokes2.model.Joke
import kotlinx.coroutines.*

class JokeViewModel : ViewModel() {

    val joke = MutableLiveData<Joke?>()
    val isLoading = MutableLiveData<Boolean>()

    var jokeUseCase = JokeUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = jokeUseCase()

            if(!result.isNullOrEmpty()){
                joke.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}