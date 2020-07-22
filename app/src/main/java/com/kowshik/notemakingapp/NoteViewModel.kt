package com.kowshik.notemakingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kowshik.notemakingapp.sqlroom.model.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val repository:modelRepository= modelRepository(application)
    val products: LiveData<List<Note>> = repository.getProductsList()
    fun insert(note: Note){
        repository.insert(note)
    }
    fun update(note: Note){
        repository.update(note)
    }
    fun delete(note: Note){
        repository.delete(note)
    }
    fun deleteAll(){
        repository.deleteAll()
    }
    fun getAllNote():LiveData<List<Note>>{
       return products
    }

}