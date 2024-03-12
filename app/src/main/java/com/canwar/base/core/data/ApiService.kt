package com.canwar.base.core.data

import com.canwar.base.feature.home.anime.data.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("anime?sfw")
    suspend fun getAnimeSearch() : Response<AnimeResponse>

}