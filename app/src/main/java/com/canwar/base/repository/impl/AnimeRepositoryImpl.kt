package com.canwar.base.repository.impl

import com.canwar.base.dataSource.ApiService
import com.canwar.base.model.app.Anime
import com.canwar.base.repository.AnimeRepository
import com.canwar.base.repository.base.BaseRepository
import com.canwar.base.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl constructor(
    private val apiService: ApiService
) : BaseRepository(), AnimeRepository {

    override suspend fun getAnimeSearch(): Flow<DataState<List<Anime>?>> = getStateOf(
        response = apiService.getAnimeSearch()
    ).map {
        // it's like you change the data from the api
        // and you can change the data to another data
        // example: change data to another data
        when (it) {
            is DataState.Success -> {
                val animeResponse = it.data
                val listAnime =
                    animeResponse?.listData?.map { animeResponse -> animeResponse.toAnime() }
                DataState.Success(listAnime)
            }

            is DataState.Error -> {
                DataState.Error(it.exception)
            }
            is DataState.Loading -> {
                DataState.Loading
            }
        }
    }

}