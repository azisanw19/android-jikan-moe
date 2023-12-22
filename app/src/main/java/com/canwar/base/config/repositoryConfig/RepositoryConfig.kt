package com.canwar.base.config.repositoryConfig

import com.canwar.base.dataSource.ApiService
import com.canwar.base.repository.AnimeRepository
import com.canwar.base.repository.impl.AnimeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryConfig {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        apiService: ApiService
    ): AnimeRepository = AnimeRepositoryImpl(apiService)


}