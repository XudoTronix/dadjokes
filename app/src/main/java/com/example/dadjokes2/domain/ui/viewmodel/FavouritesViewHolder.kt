package com.example.dadjokes2.domain.ui.viewmodel

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R


class FavouritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val joke : TextView = itemView.findViewById(R.id.itemFavJoke)
    val delete : Button = itemView.findViewById(R.id.btnDelete)
}