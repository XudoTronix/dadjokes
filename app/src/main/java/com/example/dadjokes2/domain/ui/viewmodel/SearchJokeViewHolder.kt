package com.example.dadjokes2.domain.ui.viewmodel

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R

class SearchJokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val results : TextView = itemView.findViewById(R.id.lblJoke)
}
