package com.canwar.base.feature.home.anime.data

import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import com.canwar.base.utils.data.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeRepositoryFake : AnimeRepository {
    // example: example data is not yet available
    override suspend fun getAnimeSearch(): Flow<DataState<List<AnimeData>?>> = flow {
        delay(1000)
        emit(DataState.Success(listOf(AnimeData("1", "1"))))
    }

}