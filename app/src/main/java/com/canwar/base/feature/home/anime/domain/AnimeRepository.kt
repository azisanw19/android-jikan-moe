package com.canwar.base.feature.home.anime.domain

import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.utils.data.DataState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun getAnimeSearch() : Flow<DataState<List<AnimeData>?>>

}