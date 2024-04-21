package com.example.composetask.core.data.utils

import com.example.hoorbookcompose.core.data.utils.WrappedResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.composetask.core.presentation.base.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class EmittingResponse @Inject constructor() {

    suspend fun <T : Any> makeApiCall(call: suspend () -> Response<WrappedResponse<T>>): Flow<BaseResult<WrappedResponse<T>>> {
        return flow {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.status.status) {
                    emit(BaseResult.DataState(body))
                } else {
                    emit(
                        BaseResult.ErrorState(
                            body.status.code,
                            body.status.validation_message ?: body.status.message
                        )
                    )
                }
            } else {
                if (response.code() == 401) {
                    emit(
                        BaseResult.ErrorState(
                            "401",
                            "un authenticated"
                        )
                    )
                } else {
                    val type = object : TypeToken<WrappedResponse<T>>() {}.type
                    val err: WrappedResponse<T> =
                        Gson().fromJson(response.errorBody()!!.charStream(), type)
                        emit(
                            BaseResult.ErrorState(
                                err.status.code,
                                err.status.message
                            )
                        )
                }
            }
        }
    }

}