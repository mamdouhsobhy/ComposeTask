package com.example.composetask.home.domain.interactor

import com.example.composetask.home.data.responseremote.Problem
import com.example.composetask.home.domain.repository.RoomRepositoryLocal
import javax.inject.Inject

class RoomLocalUseCase @Inject constructor(private var repository: RoomRepositoryLocal){

    suspend fun invokeMedicines(): List<Problem> = repository.getAMedicines()

    suspend fun insertMedicines(medicines: List<Problem>) {
        repository.insertMedicines(medicines)
    }

}
