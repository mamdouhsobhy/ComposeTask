package com.example.composetask.home.domain.interactor

import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import com.example.composetask.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun executeGetMedicines(): Flow<BaseState<ModelGetMedicinesResponseRemote>> {
        return homeRepository.getMedicines()
    }

}