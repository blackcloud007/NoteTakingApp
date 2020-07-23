package com.kowshik.notemakingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kowshik.notemakingapp.sqlroom.model.Note

@Suppress("DEPRECATION")
class AddNote : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    val priorityarray= arrayOf("0","1","2","3","4","5","6","7","8","9","10")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        val title=findViewById<EditText>(R.id.newtitle)
        val content:EditText=findViewById(R.id.newcontent)

        var priority:Int=0
        val spinner=findViewById<Spinner>(R.id.priority)
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,priorityarray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                priority=0
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                priority = priorityarray[p2].toInt()
            }

        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<FloatingActionButton>(R.id.save).setOnClickListener { view ->
            if (TextUtils.isEmpty(title.text)){
                title.error="Title is empty!!"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(content.text)){
                content.error="Content should not be empty"
                return@setOnClickListener
            }
           noteViewModel.insert(Note(title.text.toString().trim(),content.text.toString().trim(),priority=priority))
            Toast.makeText(this, "Note added ", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}