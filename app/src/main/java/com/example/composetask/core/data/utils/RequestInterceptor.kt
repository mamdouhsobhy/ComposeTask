package com.example.composetask.core.data.utils

import com.example.composetask.core.presentation.common.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest =
            chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()

        return newRequest.let { chain.proceed(it) }
    }
}