package com.example.dadjokes2.data

import android.content.Context
import android.util.Log
import com.example.dadjokes2.model.Joke
import com.example.dadjokes2.model.SearchJokes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    companion object {
        val BASE_URL = "https://icanhazdadjoke.com/"

        suspend fun fetchSearchData(context: Context) : ArrayList<Joke> {
            Log.d("API-DEMO", "Call to API Started")

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(DadJokesAPI::class.java)

            val result = api.getSearchJokes(1, 30,"").execute()

            return if (result.isSuccessful) {
                delay(2000)
                result.body()!!.results
            }
            else {
                Log.d("API-DEMO", "Error recuperando chistes")
                ArrayList<Joke>()
            }
        }
    }
}