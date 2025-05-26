package com.seryjnyy.notetaker.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seryjnyy.notetaker.data.local.db.dao.NoteDao
import com.seryjnyy.notetaker.data.local.db.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ], version = 3, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}