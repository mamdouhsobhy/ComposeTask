package com.example.composetask.core.data.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.composetask.core.presentation.base.BaseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class EmittingResponse @Inject constructor() {

    suspend fun <T : Any> makeApiCall(call: suspend () -> Response<T>): Flow<BaseState<T>> {
        return flow {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseState.Success(body))
            } else {
                if (response.code() == 401) {
                    emit(
                        BaseState.Error(
                            "401",
                            "un authenticated"
                        )
                    )
                } else {
                    val type = object : TypeToken<T>() {}.type
                    val err: T =
                        Gson().fromJson(response.errorBody()!!.charStream(), type)
                    emit(
                        BaseState.Error(
                            "500",
                            "SwipeToRefresh to try again!"
                        )
                    )
                }
            }
        }
    }

}