package com.canwar.base.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.canwar.base.common.data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = "com.canwar.base.settings")

class DataPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        const val KEY_THEME = "key_theme"
    }

    suspend fun saveTheme(isDarkMode: Boolean): Boolean {
        return try {
            dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(KEY_THEME)] = isDarkMode
            }
            true
        } catch (e: IOException) {
            false
        } catch (e: Exception) {
            false
        }
    }

    fun getTheme(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(KEY_THEME)] ?: false
        }.distinctUntilChanged()
}