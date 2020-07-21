package com.kowshik.notemakingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val notes: MutableList<ModelNote>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val titletext: TextView = item.findViewById<TextView>(R.id.title)
        val contenttext: TextView = item.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note: ModelNote = notes[position]
        holder.titletext.text = note.title
        holder.contenttext.text = note.content
    }
}