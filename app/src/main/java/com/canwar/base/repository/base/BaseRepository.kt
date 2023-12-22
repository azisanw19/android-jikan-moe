package com.canwar.base.repository.base

import com.canwar.base.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> getStateOf(response: Response<T>) : Flow<DataState<T?>> = flow {
        emit(DataState.Loading)
        if (response.code() == 200) {
            emit(DataState.Success(response.body()))
        } else {
            // handle error
            emit(DataState.Error(Exception()))
        }
    }

}