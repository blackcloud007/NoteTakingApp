package com.kowshik.notemakingapp

import android.app.Application
import androidx.lifecycle.LiveData
import com.kowshik.notemakingapp.sqlroom.NotesDB
import com.kowshik.notemakingapp.sqlroom.dao.NoteDAO
import com.kowshik.notemakingapp.sqlroom.executor.AppExecutors
import com.kowshik.notemakingapp.sqlroom.model.Note

class modelRepository(application: Application){
    private val noteDAO:NoteDAO
    private val notes:LiveData<List<Note>>
    private val database:NotesDB = NotesDB.getDatabase(application)
    init {
       noteDAO =database.noteDAO()
        notes = noteDAO.getNotes()
    }
    fun insert(note: Note){
        AppExecutors.instance?.diskIO()?.execute(Runnable {
           noteDAO.insert(note)
        })
    }
    fun update(note: Note){
        AppExecutors.instance?.diskIO()?.execute(Runnable {
            noteDAO.update(note)
        })
    }
    fun delete(note: Note){
        AppExecutors.instance?.diskIO()?.execute(Runnable {
            noteDAO.delete(note)
        })
    }
    fun deleteAll(){
        AppExecutors.instance?.diskIO()?.execute(Runnable {
            noteDAO.deleteAll()
        })
    }
    fun getProductsList():LiveData<List<Note>>{
        return notes
    }
}