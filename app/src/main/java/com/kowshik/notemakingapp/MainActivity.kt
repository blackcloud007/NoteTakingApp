package com.kowshik.notemakingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kowshik.notemakingapp.sqlroom.NotesDB
import com.kowshik.notemakingapp.sqlroom.dao.NoteDAO
import com.kowshik.notemakingapp.sqlroom.model.Note
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var rv_notelist: RecyclerView
    lateinit var noteViewModel: NoteViewModel
    lateinit var notes: List<Note>
    lateinit var db: NotesDB
    lateinit var notesdao: NoteDAO
    lateinit var add: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        adpaterInitialization()
        intializeObservers()
        initListeners()
    }

    private fun intializeObservers() {
        noteViewModel.getAllNote()
            .observe(this, Observer<List<Note>> {
                val list: List<Note> = it
                rv_notelist.adapter = this.let {
                   Adapter(
                       notes = list,
                       context = this
                    )
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) {
                noteViewModel.deleteAll()
            Toast.makeText(this, "All items are deleted!!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initListeners() {
        add.setOnClickListener { view ->
            startActivity(Intent(this, AddNote::class.java))
        }
    }

    private fun adpaterInitialization() {
        rv_notelist.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_notelist.setHasFixedSize(true)
    }

    private fun initViews() {
        rv_notelist = findViewById(R.id.rv_notelist)
        add = findViewById<FloatingActionButton>(R.id.add)
        db = NotesDB.getDatabase(applicationContext)
        notesdao = db.noteDAO()
        notes = ArrayList<Note>()
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }
}