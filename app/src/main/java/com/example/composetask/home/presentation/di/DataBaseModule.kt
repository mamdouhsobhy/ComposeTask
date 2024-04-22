package com.example.composetask.home.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.composetask.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class DataBaseModule {
     //var converter:Converter= Converter()
    @Provides
    @Singleton
    fun provideDB(application: Application): RoomDB = Room.databaseBuilder(application, RoomDB::class.java, "problem_tab")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


}