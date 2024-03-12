package com.canwar.base.di

import android.content.Context
import com.canwar.base.core.data.DataPreference
import com.canwar.base.core.data.preferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataPreference(
        @ApplicationContext context: Context
    ) = DataPreference(context.preferenceDataStore)

}