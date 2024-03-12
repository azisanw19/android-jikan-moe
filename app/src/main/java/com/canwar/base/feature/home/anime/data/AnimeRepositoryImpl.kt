package com.canwar.base.feature.home.anime.data

import com.canwar.base.core.data.ApiService
import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import com.canwar.base.core.data.BaseRepository
import com.canwar.base.common.data.DataState
import com.canwar.base.common.util.DispatcherProvider

class AnimeRepositoryImpl(
    dispatcherProvider: DispatcherProvider,
    private val apiService: ApiService
) : BaseRepository(dispatcherProvider), AnimeRepository {

    override suspend fun getAnimeSearch(): DataState<List<AnimeData>> = getStateOf(
        response = {
            apiService.getAnimeSearch()
        },
        success = { response ->
            val listAnime =
                response.listData?.map { animeModel -> animeModel.toAnimeData() } ?: emptyList()
            DataState.Success(listAnime)
        },
    )

}