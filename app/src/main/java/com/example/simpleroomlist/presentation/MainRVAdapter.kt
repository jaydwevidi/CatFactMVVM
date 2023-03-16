package com.example.simpleroomlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.simpleroomlist.R
import com.example.simpleroomlist.data.remote.CatFactResponse


class MainRVAdapter(var dataset : MutableList<CatFactResponse>) : RecyclerView.Adapter<MainRVAdapter.MainViewHolder>() {

    class MainViewHolder(view : View) : ViewHolder(view) {
        val mainText : TextView

        init {
            mainText = view.findViewById(R.id.rv_item_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)

        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.mainText.text = dataset[position].toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}