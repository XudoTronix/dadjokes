package com.example.dadjokes2.domain.ui.viewmodel

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R
import com.example.dadjokes2.model.FavouriteJoke
import com.google.firebase.firestore.FirebaseFirestore

class FavouritesAdapter(val favList: ArrayList<FavouriteJoke>) : RecyclerView.Adapter<FavouritesViewHolder>() {

    var onItemClick : ((FavouriteJoke) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.favourites_item, parent, false)
        return FavouritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val joke: FavouriteJoke = favList[position]
        holder.joke.text = joke.joke
        holder.delete.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete")
            builder.setMessage("The joke will be deleted, continue?")
            builder.setPositiveButton("yes") { dialogInteface: DialogInterface, i: Int ->
                val del = db.collection("favourites").document(joke.iddoc!!)
                db.runBatch { batch ->
                    batch.delete(del)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Joke deleted", Toast.LENGTH_LONG).show()
                    favList.removeAt(position)
                    notifyDataSetChanged()
                }
            }
            builder.setNegativeButton("no") { dialogInteface: DialogInterface, i: Int ->
            }
            builder.show()
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(joke)
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }
}
