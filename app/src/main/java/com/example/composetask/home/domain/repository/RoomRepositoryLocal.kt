package com.example.composetask.home.domain.repository


import com.example.composetask.home.data.responseremote.Problem

interface RoomRepositoryLocal {

    suspend fun insertMedicines(medicines: List<Problem>)

    suspend fun getAMedicines(): List<Problem>

}