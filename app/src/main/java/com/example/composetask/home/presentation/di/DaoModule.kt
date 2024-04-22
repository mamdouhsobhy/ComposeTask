package com.example.composetask.home.presentation.di

import com.example.composetask.home.data.repositoryimb.RoomRepositoryLocalImp
import com.example.composetask.home.domain.repository.RoomRepositoryLocal
import com.example.composetask.room.RoomDB
import com.example.composetask.room.RoomDao
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideDao(roomDB: RoomDB): RoomDao = roomDB.redDao()!!


    @Provides
    @Singleton
    fun provideRoomRepositoryLocal(roomDao: RoomDao): RoomRepositoryLocal =
        RoomRepositoryLocalImp(roomDao)


}