package com.example.dadjokes2.domain.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R
import com.example.dadjokes2.data.ApiRepository
import com.example.dadjokes2.model.Joke
import com.example.dadjokes2.domain.ui.viewmodel.SearchJokeAdapter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity() {


    private val coroutineContext: CoroutineContext = newSingleThreadContext("main")
    private val scope = CoroutineScope(coroutineContext)

    private var searchjokes = ArrayList<Joke>()
    private lateinit var adapter: SearchJokeAdapter

    private lateinit var rvSearchjokes: RecyclerView
    private lateinit var getSearchTerm: EditText
    private lateinit var getSearchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        getSearchButton = findViewById(R.id.showInput)
        getSearchTerm = findViewById(R.id.editText)

        rvSearchjokes = findViewById<RecyclerView>(R.id.rvJoke)
        rvSearchjokes.layoutManager = LinearLayoutManager(this)
        adapter = SearchJokeAdapter(searchjokes, this)
        rvSearchjokes.adapter= adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetailedSearchActivity::class.java)
            intent.putExtra("joke", it)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getSearchButton.setOnClickListener {
            start(this, getSearchTerm.text.toString())
        }


    }

    fun start(context: Context, term: String) {
        scope.launch {

            searchjokes = ApiRepository().fetchSearchData(context, term)
            Log.d("API-DEMO", searchjokes.size.toString())
            withContext(Dispatchers.Main) {
                adapter.Update(searchjokes)
            }
        }
    }
}
