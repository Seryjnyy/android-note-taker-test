package com.seryjnyy.notetaker.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seryjnyy.notetaker.data.entity.local.NoteLocalEntity

@Database(
    entities = [
    NoteLocalEntity::class
    ], version = 5, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}