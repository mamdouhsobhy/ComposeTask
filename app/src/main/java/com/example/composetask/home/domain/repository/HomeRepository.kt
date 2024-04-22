package com.example.composetask.home.domain.repository

import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getMedicines(): Flow<BaseState<ModelGetMedicinesResponseRemote>>

}