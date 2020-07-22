package com.kowshik.notemakingapp.sqlroom.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kowshik.notemakingapp.sqlroom.model.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("select * from Note order by priority desc")
    fun getNotes(): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)

    @Query("Delete from Note")
    fun deleteAll()

}