package com.example.composetask.home.domain.interactor

import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.repositoryimb.FakeHomeRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class HomeUseCaseTest {

    private lateinit var homeUseCase: HomeUseCase
    private lateinit var fakeHomeRepositoryImpl: FakeHomeRepositoryImpl

    @Before
    fun setUp(){
        fakeHomeRepositoryImpl = FakeHomeRepositoryImpl()
        homeUseCase = HomeUseCase(fakeHomeRepositoryImpl)
    }

    @Test
    fun `testGetMedicinesCorrectListReturn`(): Unit = runBlocking{
        homeUseCase.executeGetMedicines().collect { state ->
            when (state) {
                is BaseState.Success -> {
                    val medicationName = state.items?.problems?.get(0)?.Diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(0)?.className?.get(0)?.associatedDrug?.get(0)?.name
                    assertThat(medicationName).isEqualTo("Drug 1")
                }
                else -> Unit
            }
        }
    }

    @Test
    fun `testGetMedicinesFailedListReturn`(): Unit = runBlocking{
        homeUseCase.executeGetMedicines().collect { state ->
            when (state) {
                is BaseState.Success -> {
                    val medicationName = state.items?.problems?.get(1)?.Diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(0)?.className?.get(0)?.associatedDrug?.get(0)?.name
                    assertThat(medicationName).isEqualTo("Drug 1")
                }
                else -> Unit
            }
        }
    }

}
