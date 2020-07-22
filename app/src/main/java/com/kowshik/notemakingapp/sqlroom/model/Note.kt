package com.kowshik.notemakingapp.sqlroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(val title:String, val content:String,val priority:Int=0, @PrimaryKey(autoGenerate = true) val id:Int=0)