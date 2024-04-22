package com.example.composetask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composetask.home.data.responseremote.Problem

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMedicines(medicines: List<Problem>)

    @Query("select * from problem_tab")
    fun getMedicines(): List<Problem>?

}