package com.kowshik.notemakingapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kowshik.notemakingapp.sqlroom.model.Note
import kotlinx.android.synthetic.main.content_note_details.view.*

class Adapter(val context: Context,private val notes: List<Note>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val titletext: TextView = item.findViewById<TextView>(R.id.title)
        val contenttext: TextView = item.findViewById(R.id.content)
        val priority:TextView=item.findViewById(R.id.prioritystar)
        val view:View=item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.titletext.text = note.title
        holder.contenttext.text = note.content
        holder.priority.text=note.priority.toString()
        holder.view.setOnClickListener{
            val intent=Intent(it.context,NoteDetails::class.java)
            intent.putExtra("title",note.title.trim())
            intent.putExtra("content",note.content.trim())
            intent.putExtra("priority",note.priority.toString().trim())
            intent.putExtra("id",note.id.toString().trim())
            it.context.startActivity(intent)
        }
    }
}