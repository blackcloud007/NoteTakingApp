package com.kowshik.notemakingapp.sqlroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kowshik.notemakingapp.sqlroom.dao.NoteDAO
import com.kowshik.notemakingapp.sqlroom.executor.AppExecutors
import com.kowshik.notemakingapp.sqlroom.model.Note

// Annotates class to be a Room Database with a table (entity) of the product class
    @Database(entities = [Note::class], version = 2, exportSchema = false)
    abstract class NotesDB : RoomDatabase() {

        abstract fun noteDAO(): NoteDAO

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: NotesDB? = null

            fun getDatabase(context: Context): NotesDB {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDB::class.java,
                        "note_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }

        }


    }