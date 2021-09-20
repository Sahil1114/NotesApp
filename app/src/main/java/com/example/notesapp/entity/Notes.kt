package com.example.notesapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Notes")
class Notes (
    @PrimaryKey(autoGenerate = true)
    var id :Int?=null,
    var title : String,
    var subtitle : String,
    var notes : String,
    var date : String,
    var priority :String
) :Serializable