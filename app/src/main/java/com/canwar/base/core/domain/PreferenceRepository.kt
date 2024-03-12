package com.canwar.base.core.domain

import com.canwar.base.common.data.DataState

interface PreferenceRepository {

    suspend fun setTheme(isDarkTheme: Boolean) : DataState<Boolean>
    suspend fun getTheme() : DataState<Boolean>

}