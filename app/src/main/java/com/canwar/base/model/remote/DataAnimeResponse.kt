package com.canwar.base.model.remote

import com.canwar.base.model.app.Anime
import com.google.gson.annotations.SerializedName

data class DataAnimeResponse (
    @SerializedName("data")
    val title: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
) {
    fun toAnime() = Anime(
        title = title,
        synopsis = synopsis,
    )
}