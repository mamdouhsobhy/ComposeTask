package com.example.composetask.home.data.datasource

import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import retrofit2.Response
import retrofit2.http.*

interface HomeService {

    @GET("v3/f7994193-c34d-4bb7-ba40-1c3d0abba515")
    suspend fun getMedicines(): Response<ModelGetMedicinesResponseRemote>

}