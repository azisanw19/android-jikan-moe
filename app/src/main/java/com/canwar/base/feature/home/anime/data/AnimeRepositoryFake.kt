package com.canwar.base.feature.home.anime.data

import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import com.canwar.base.common.data.DataState
import com.canwar.base.common.util.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AnimeRepositoryFake(
    private val dispatcherProvider: DispatcherProvider
) : AnimeRepository {
    // example: example data is not yet available
    override suspend fun getAnimeSearch(): DataState<List<AnimeData>> = withContext(dispatcherProvider.io) {
        delay(2000)
        DataState.Success(emptyList())
    }

}