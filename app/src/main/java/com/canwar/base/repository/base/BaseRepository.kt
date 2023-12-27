package com.canwar.base.repository.base

import com.canwar.base.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> getStateOf(responseFunction: suspend () -> Response<T>) : Flow<DataState<T?>> = flow {
        emit(DataState.Loading)
        val response = responseFunction()
        if (response.code() == 200) {
            emit(DataState.Success(response.body()))
        } else {
            // handle error
            // code 400, 500, etc
            emit(DataState.Error(Exception()))
        }
    }

}