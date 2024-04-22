package com.example.composetask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composetask.home.data.responseremote.Problem
@TypeConverters(DiabeteListConverter::class, AsthmaListConverter::class)
@Database(entities = [Problem::class], version =1 ,exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun redDao(): RoomDao?
}