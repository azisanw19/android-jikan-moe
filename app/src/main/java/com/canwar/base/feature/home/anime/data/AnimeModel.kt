package com.canwar.base.feature.home.anime.data

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val listData: List<AnimeModel>?
)

data class AnimeModel (
    @SerializedName("data")
    val title: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
)