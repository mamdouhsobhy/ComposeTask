package com.example.composetask.home.data.responseremote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_tab")
data class ModelGetMedicinesResponseRemote(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val problems: List<Problem>?
)