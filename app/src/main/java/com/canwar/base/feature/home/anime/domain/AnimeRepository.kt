package com.canwar.base.feature.home.anime.domain

import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.common.data.DataState

interface AnimeRepository {

    suspend fun getAnimeSearch() : DataState<List<AnimeData>>

}