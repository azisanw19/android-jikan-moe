package com.canwar.base.core.data

import com.canwar.base.utils.data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> getStateOf(responseFunction: suspend () -> Response<T>) : Flow<DataState<T?>> = flow {
        try {
            val response = responseFunction()
            if (response.isSuccessful) {
                emit(DataState.Success(response.body()))
            } else {
                emit(DataState.Error(response.message()))
            }
        } catch (e: Exception) {
            // handle exception
            emit(DataState.Error(e.message ?: "Something went wrong! Please try again later."))
        }
    }

}