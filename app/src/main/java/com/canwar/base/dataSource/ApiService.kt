package com.canwar.base.dataSource

import com.canwar.base.model.remote.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("anime?sfw")
    suspend fun getAnimeSearch() : Response<AnimeResponse>

}