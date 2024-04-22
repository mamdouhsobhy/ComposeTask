package com.example.composetask.home.data.repositoryimb

import com.example.composetask.core.data.utils.EmittingResponse
import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.datasource.HomeService
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import com.example.composetask.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val emittingResponse: EmittingResponse
) : HomeRepository {
    override suspend fun getMedicines(): Flow<BaseState<ModelGetMedicinesResponseRemote>> {
        return emittingResponse.makeApiCall { homeService.getMedicines() }
    }

}