package com.example.composetask.home.data.responseremote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asthma_tab")
data class Asthma(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "nothing")
    val nothing: String? = ""
)