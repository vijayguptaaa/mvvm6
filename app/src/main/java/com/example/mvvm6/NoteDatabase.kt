package com.example.mvvm6

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao() : NoteDao

    //singelton banane ke liye companion object use karna hoga

    //synchronized isliye lagate hain taki koi or thread agar isko access karna chahe to na kar paye
    //singelton wo classes hoti hain jaha bas ek hi instance banaya ja skta hai
    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase?= null

        fun getDatabae(context: Context) : NoteDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                "notes_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}