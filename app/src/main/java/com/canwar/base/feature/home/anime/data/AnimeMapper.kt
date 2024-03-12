package com.canwar.base.feature.home.anime.data

import com.canwar.base.feature.home.anime.domain.model.AnimeData

fun AnimeModel.toAnimeData(): AnimeData {
    return AnimeData(
        title = title,
        synopsis = synopsis,
    )
}