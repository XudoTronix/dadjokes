package com.example.dadjokes2.data

import android.content.Context
import com.example.dadjokes2.model.Joke

class ApiRepository {

    suspend fun fetchSearchData(context: Context, term: String): ArrayList<Joke> {
        return APIService.fetchSearchData(context, term)
    }
}