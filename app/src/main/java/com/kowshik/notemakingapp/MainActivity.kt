package com.kowshik.notemakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var rv_notelist:RecyclerView
    lateinit var notes:MutableList<ModelNote>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        populateNotes()
        rv_notelist.adapter=Adapter(notes)
        rv_notelist.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }

    private fun populateNotes() {
        notes=ArrayList<ModelNote>()//do i Actually need this?
        notes.add(ModelNote("Title 1","This is my Content!!...................................................................................................................................................."))
        notes.add(ModelNote("Title 2","This is my Content!!"))
        notes.add(ModelNote("Title 3","This is my Content!!"))
        notes.add(ModelNote("Title 4","This is my Content!!"))
        notes.add(ModelNote("Title 5","This is my Content!!"))
        notes.add(ModelNote("Title 6","This is my Content!!"))
    }

    private fun initViews() {
        rv_notelist=findViewById(R.id.rv_notelist)
    }
}