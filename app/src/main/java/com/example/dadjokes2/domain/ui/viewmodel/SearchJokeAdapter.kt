package com.example.dadjokes2.domain.ui.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes2.R
import com.example.dadjokes2.model.Joke

class SearchJokeAdapter(var items: MutableList<Joke>, context: Context) : RecyclerView.Adapter<SearchJokeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchJokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searchjoke_item, parent, false)
        return SearchJokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchJokeViewHolder, position: Int) {
        holder.results.text = items[position].joke
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun Update(items_new: MutableList<Joke>) {
        items = items_new
        this.notifyDataSetChanged()
    }
}