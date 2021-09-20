package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.entity.Notes

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase :RoomDatabase() {
    abstract fun getNotesDao():NotesDao
    companion object{
        @Volatile
        var Instance :NoteDatabase?=null

        fun getDatabaseInstance(context: Context) :NoteDatabase{
            val tempInstance = Instance
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "Notes"
                ).allowMainThreadQueries().build()
                Instance=roomDatabaseInstance
                return return roomDatabaseInstance

            }

        }
    }
}