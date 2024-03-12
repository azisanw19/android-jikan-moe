package com.canwar.base.core.data

import com.canwar.base.core.domain.PreferenceRepository
import com.canwar.base.common.data.DataState
import com.canwar.base.common.util.DispatcherProvider
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferenceRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val dataPreference: DataPreference
) : PreferenceRepository {
    override suspend fun setTheme(isDarkTheme: Boolean): DataState<Boolean> {
        return withContext(dispatcherProvider.io) {
            if (dataPreference.saveTheme(isDarkTheme)) {
                DataState.Success(true)
            } else {
                DataState.Error("Error saving theme")
            }
        }
    }

    override suspend fun getTheme(): DataState<Boolean> =
        dataPreference.getTheme().flowOn(dispatcherProvider.io).map {
            DataState.Success(it)
        }.runCatching {
            DataState.Error("Error getting theme")
        }.getOrNull() ?: DataState.Error("Error getting theme")
}