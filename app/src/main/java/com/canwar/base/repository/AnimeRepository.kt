package com.canwar.base.repository

import com.canwar.base.model.app.Anime
import com.canwar.base.utils.state.DataState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun getAnimeSearch() : Flow<DataState<List<Anime>?>>

}