package com.example.composetask.home.data.responseremote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problem_tab")
data class Problem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val Asthma: List<Asthma>?,
    val Diabetes: List<Diabete>?
)