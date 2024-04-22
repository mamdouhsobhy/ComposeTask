package com.example.composetask.room

import androidx.room.TypeConverter
import com.example.composetask.home.data.responseremote.Diabete
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DiabeteListConverter {
    @TypeConverter
    fun fromDiabeteList(value: List<Diabete>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toDiabeteList(value: String?): List<Diabete>? {
        val type = object : TypeToken<List<Diabete>>() {}.type
        return Gson().fromJson(value, type)
    }
}
