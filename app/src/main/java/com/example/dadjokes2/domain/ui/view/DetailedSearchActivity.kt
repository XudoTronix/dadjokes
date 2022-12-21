package com.example.dadjokes2.domain.ui.view

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dadjokes2.R
import com.example.dadjokes2.model.Joke

class DetailedSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_search)

        showImage(this)
    }

    fun showImage(context: Context) {
        val joke = intent.getParcelableExtra<Joke>("joke")
        if (joke != null) {
            val imageView : ImageView = findViewById(R.id.jokeImageView)
            val url  = "https://icanhazdadjoke.com/j/${joke.id}.png"

            Glide.with(context)
                .load(url)
                .fitCenter()
                .into(imageView)
        }
    }
}