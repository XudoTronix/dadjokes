package com.example.dadjokes2.data

import android.content.Context
import com.example.dadjokes2.model.Joke

class ApiRepository {

    private val api = APIService()

    suspend fun fetchSearchData(context: Context): ArrayList<Joke> {
        return APIService.fetchSearchData(context)
    }
}