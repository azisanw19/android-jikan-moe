package com.canwar.base.di

import com.canwar.base.common.util.DispatcherProvider
import com.canwar.base.core.data.ApiService
import com.canwar.base.core.data.DataPreference
import com.canwar.base.core.data.PreferenceRepositoryImpl
import com.canwar.base.core.domain.PreferenceRepository
import com.canwar.base.feature.home.anime.data.AnimeRepositoryImpl
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAnimeRepository(
        dispatcherProvider: DispatcherProvider,
        apiService: ApiService
    ): AnimeRepository = AnimeRepositoryImpl(dispatcherProvider, apiService)

    @Provides
    fun providePreferenceRepository(
        dispatcherProvider: DispatcherProvider,
        dataPreference: DataPreference
    ): PreferenceRepository = PreferenceRepositoryImpl(dispatcherProvider, dataPreference)
}