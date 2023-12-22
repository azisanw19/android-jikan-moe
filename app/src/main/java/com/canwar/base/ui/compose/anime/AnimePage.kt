package com.canwar.base.ui.compose.anime

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AnimePage(
    animeViewModel: AnimeViewModel = hiltViewModel()
) {
    animeViewModel.getAnime()
    val listAnime = animeViewModel.data.collectAsState(initial = listOf())

    LaunchedEffect(Unit) {
        animeViewModel.isLoading.collect {
        }
    }

    LazyColumn {
        itemsIndexed(listAnime.value) {index, item ->
            Text(text = item.synopsis ?: "Kukurruuu")
        }
    }

}