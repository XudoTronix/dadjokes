package com.example.dadjokes2.domain.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R
import com.example.dadjokes2.domain.ui.viewmodel.FavouritesAdapter
import com.example.dadjokes2.model.FavouriteJoke
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class FavouritesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favList: ArrayList<FavouriteJoke>
    private lateinit var adapter: FavouritesAdapter
    private lateinit var db: FirebaseFirestore


    private val getEmailFirebase = FirebaseAuth.getInstance().currentUser!!.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        recyclerView = findViewById(R.id.rvFavJoke)
        recyclerView.layoutManager = LinearLayoutManager(this)

        favList = arrayListOf()

        adapter = FavouritesAdapter(favList)

        recyclerView.adapter = adapter

        eventChangeListener()

        adapter.onItemClick = {
            val intent = Intent(this, DetailedFavouriteActivity::class.java)
            intent.putExtra("joke", it)
            startActivity(intent)
        }
    }




    private fun eventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("favourites").
            whereEqualTo("email", getEmailFirebase!!).
            addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null){
                        Log.e("Firestore", error.message.toString())
                    }
                    for (dc : DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            val wallItem = dc.document.toObject(FavouriteJoke::class.java)
                            wallItem.iddoc = dc.document.id
                            wallItem.id = dc.document["id"].toString()
                            wallItem.joke = dc.document["joke"].toString()
                            wallItem.email = dc.document["email"].toString()
                            favList.add(wallItem)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

            })
    }
}