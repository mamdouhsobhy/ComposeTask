package com.example.composetask.home.presentation.di

import com.example.composetask.core.data.module.NetworkModule
import com.example.composetask.core.data.utils.EmittingResponse
import com.example.composetask.home.data.datasource.HomeService
import com.example.composetask.home.data.repositoryimb.HomeRepositoryImpl
import com.example.composetask.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(homeService: HomeService,emittingResponse: EmittingResponse) : HomeRepository {
        return HomeRepositoryImpl(homeService,emittingResponse)
    }


}