package com.canwar.base.core.data

import com.canwar.base.common.data.DataState
import com.canwar.base.common.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository(
    private val dispatcherProvider: DispatcherProvider,
) {

    protected suspend fun <T, R> getStateOf(
        response: suspend () -> Response<T>,
        success: (result: T) -> DataState<R>,
    ): DataState<R> =
        withContext(dispatcherProvider.io) {
            try {
                val result = response()
                if (result.isSuccessful) {
                    success(result.body()!!)
                } else {
                    DataState.Error(result.message())
                }
            } catch (e: Exception) {
                // handle exception
                DataState.Error(e.message ?: "Something went wrong! Please try again later.")
            }
        }
}