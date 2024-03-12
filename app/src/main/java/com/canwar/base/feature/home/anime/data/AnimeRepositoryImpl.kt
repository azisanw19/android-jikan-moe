package com.canwar.base.feature.home.anime.data

import com.canwar.base.core.data.ApiService
import com.canwar.base.feature.home.anime.domain.model.AnimeData
import com.canwar.base.feature.home.anime.domain.AnimeRepository
import com.canwar.base.core.data.BaseRepository
import com.canwar.base.utils.data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), AnimeRepository {

    override suspend fun getAnimeSearch(): Flow<DataState<List<AnimeData>?>> = getStateOf {
        apiService.getAnimeSearch()
    }
        .map {
            // it's like you change the data from the api
            // and you can change the data to another data
            // example: change data to another data
            when (it) {
                is DataState.Success -> {
                    val response = it.data
                    val listAnime =
                        response?.listData?.map { animeModel -> animeModel.toAnimeData() }
                    DataState.Success(listAnime)
                }
                is DataState.Error -> {
                    DataState.Error(it.message)
                }
            }
        }

}