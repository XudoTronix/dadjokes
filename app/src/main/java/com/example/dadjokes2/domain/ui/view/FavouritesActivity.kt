package com.example.dadjokes2.domain.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R
import com.example.dadjokes2.data.ApiRepository
import com.example.dadjokes2.model.Joke
import com.example.dadjokes2.domain.ui.viewmodel.SearchJokeAdapter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FavouritesActivity : AppCompatActivity() {

    //private val coroutineContext: CoroutineContext = newSingleThreadContext("main")
    //private val scope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        /*rvSearchjokes = findViewById<RecyclerView>(R.id.rvJoke)
        rvSearchjokes.layoutManager = LinearLayoutManager(this)
        adapter = SearchJokeAdapter(searchjokes, this)
        rvSearchjokes.adapter= adapter
    }

    override fun onStart() {
        super.onStart()
        start(this)
    }

    fun start(context: Context) {
        scope.launch {

            searchjokes = ApiRepository().fetchSearchData(context)
            Log.d("API-DEMO", searchjokes.size.toString())
            // Log.d("API-DEMO", universities.toString())
            withContext(Dispatchers.Main) {
                adapter.Update(searchjokes)
            }
        }
    }*/
    }
}