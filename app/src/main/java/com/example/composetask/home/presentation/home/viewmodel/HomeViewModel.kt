package com.example.composetask.home.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.core.data.utils.ExecuteResponse
import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import com.example.composetask.home.data.responseremote.Problem
import com.example.composetask.home.domain.interactor.HomeUseCase
import com.example.composetask.home.domain.interactor.RoomLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val executeResponse: ExecuteResponse,
    private val roomLocalUseCase: RoomLocalUseCase
) : ViewModel() {

    private val _homeState =
        MutableStateFlow<BaseState<ModelGetMedicinesResponseRemote>>(BaseState.Init)
    val homeState: StateFlow<BaseState<ModelGetMedicinesResponseRemote>> get() = _homeState

    init {
        getMedicines()
    }
    fun getMedicines() {
        viewModelScope.launch {
            executeResponse.execute(homeUseCase.executeGetMedicines(), _homeState)
                .collect {
                    _homeState.value = it
                }
        }
    }

    suspend fun insertMedicines(medicines: List<Problem>?) {
        medicines?.let { roomLocalUseCase.insertMedicines(it) }
    }
    suspend fun getMedicinesLocal(){
        val modelGetMedicinesResponseRemote = ModelGetMedicinesResponseRemote(problems = roomLocalUseCase.invokeMedicines())
        _homeState.value = BaseState.Success(modelGetMedicinesResponseRemote)
    }

    fun insertProblems(medicines: List<Problem>?){
        viewModelScope.launch {
            insertMedicines(medicines)
        }
    }

    fun getMedicinesFromRoom(){
        viewModelScope.launch {
            getMedicinesLocal()
        }
    }

}
