package com.canwar.base.model.remote

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val listData: List<DataAnimeResponse>?
)
