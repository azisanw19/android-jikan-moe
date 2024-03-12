package com.canwar.base.di

import com.canwar.base.core.data.ApiService
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
    @Singleton
    fun provideAnimeRepository(
        apiService: ApiService
    ): AnimeRepository = AnimeRepositoryImpl(apiService)


}