package com.seryjnyy.notetaker.di

import android.app.Application
import androidx.room.Room
import com.seryjnyy.notetaker.data.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase) = appDatabase.noteDao()
}