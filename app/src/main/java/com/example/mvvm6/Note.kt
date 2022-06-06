package com.example.mvvm6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity(tableName = "notes_table")
class Note(
            @ColumnInfo(name = "text")
            val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
