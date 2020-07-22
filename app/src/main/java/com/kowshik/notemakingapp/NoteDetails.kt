package com.kowshik.notemakingapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kowshik.notemakingapp.sqlroom.NotesDB
import com.kowshik.notemakingapp.sqlroom.executor.AppExecutors
import com.kowshik.notemakingapp.sqlroom.model.Note

@Suppress("DEPRECATION")
class NoteDetails : AppCompatActivity() {
    lateinit var db: NotesDB
    val priorityarray= arrayOf("0","1","2","3","4","5","6","7","8","9","10")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db= NotesDB.getDatabase(this)
        val intent=intent
        val content=findViewById<TextView>(R.id.noteContent)
        val title:TextView=findViewById(R.id.noteTitle)
        val noteViewModel: NoteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        content.movementMethod=ScrollingMovementMethod()
        title.text=intent.getStringExtra("title")
        content.text=intent.getStringExtra("content")
        val id=intent.getStringExtra("id")
        val priorityold=intent.getStringExtra("priority")

        val spinner=findViewById<Spinner>(R.id.updatePriority)
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,priorityarray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        val pos=adapter.getPosition(priorityold.toString().trim())
        spinner.setSelection(pos)
        var priority:Int=priorityold.toString().toInt()
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                priority=0
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                priority = priorityarray[p2].toInt()
            }

        }

        findViewById<FloatingActionButton>(R.id.update).setOnClickListener { view ->
            if (TextUtils.isEmpty(title.text)){
                title.error="Title is empty!!"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(content.text)){
                content.error="Content should not be empty"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(id.toString())){
                Toast.makeText(this,"ID error +$id ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            noteViewModel.update(Note(title.text.toString(),content.text.toString(),priority =priority,id = id.toString().toInt()))
            Toast.makeText(this, "Note Updated ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}