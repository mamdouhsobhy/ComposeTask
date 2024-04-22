package com.example.composetask.home.data.repositoryimb

import com.example.composetask.home.data.responseremote.Problem
import com.example.composetask.home.domain.repository.RoomRepositoryLocal
import com.example.composetask.room.RoomDao
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data from  db
 */
class RoomRepositoryLocalImp @Inject constructor(private val roomDao: RoomDao) :
    RoomRepositoryLocal {


    override suspend fun insertMedicines( medicines: List<Problem>) {
        roomDao.insertMedicines(medicines)
    }

    override suspend fun getAMedicines(): List<Problem> {
        return roomDao.getMedicines()!!
    }
}