package com.example.dadjokes2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.dadjokes2.databinding.ActivityMainBinding
import com.example.dadjokes2.domain.ui.view.FavouritesActivity
import com.example.dadjokes2.domain.ui.view.LoginActivity
import com.example.dadjokes2.domain.ui.view.SearchActivity
import com.example.dadjokes2.domain.ui.viewmodel.JokeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var btnLogout : Button
    private lateinit var btnToSearchActivity: Button
    private lateinit var btnToFavourites: Button
    private lateinit var btnToGenerateJoke: Button
    private lateinit var btnAddFavourites: Button
    private lateinit var binding: ActivityMainBinding

    val db = Firebase.firestore

    val getEmailFirebase = FirebaseAuth.getInstance().currentUser!!.email

    private val jokeViewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // onCreate
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //Button
        btnToSearchActivity = findViewById(R.id.btnToSearchActivity)
        btnToSearchActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }

        btnToFavourites = findViewById(R.id.btnToFavoriteActivity)
        btnToFavourites.setOnClickListener {
            val intent = Intent(this@MainActivity, FavouritesActivity::class.java)
            startActivity(intent)
        }

        btnLogout = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        btnToGenerateJoke = findViewById(R.id.btnGenerateJoke)
        btnToGenerateJoke.setOnClickListener {
            jokeViewModel.onCreate()
            jokeViewModel.joke.observe(this, Observer {
                binding.tvJoke.text = it!!.joke
            })
        }



        btnAddFavourites = findViewById(R.id.btnAddFavourites)
        btnAddFavourites.setOnClickListener {
            db.collection("favourites").document(getEmailFirebase!!).set(
                hashMapOf(
                    "id" to "jokeViewModel.joke.value!!.id",
                    "joke" to "jokeViewModel.joke.value!!.joke"
                )
            )
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            Log.d("LOGIN-ACTIVITY", "Usuario no logueado")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}