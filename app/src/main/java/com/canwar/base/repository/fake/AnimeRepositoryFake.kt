package com.canwar.base.repository.fake

import com.canwar.base.model.app.Anime
import com.canwar.base.repository.AnimeRepository
import com.canwar.base.utils.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeRepositoryFake : AnimeRepository {
    // example: example data is not yet available
    override suspend fun getAnimeSearch(): Flow<DataState<List<Anime>?>> = flow {
        emit(DataState.Loading)
        delay(1000)
        emit(DataState.Success(listOf(Anime("1", "1"))))
    }

}