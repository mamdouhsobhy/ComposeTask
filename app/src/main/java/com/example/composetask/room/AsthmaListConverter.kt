package com.example.composetask.room

import androidx.room.TypeConverter
import com.example.composetask.home.data.responseremote.Asthma
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AsthmaListConverter {
    @TypeConverter
    fun fromAsthmaList(value: List<Asthma>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAsthmaList(value: String?): List<Asthma>? {
        val type = object : TypeToken<List<Asthma>>() {}.type
        return Gson().fromJson(value, type)
    }
}
