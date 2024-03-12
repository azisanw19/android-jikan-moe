package com.canwar.base.feature.home.anime.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canwar.base.R
import com.canwar.base.core.presentation.component.DialogLoading

const val ANIME_ROUTE = "anime-route"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel = hiltViewModel()
) {
    val listAnime = animeViewModel.data.collectAsState(initial = listOf())
    val isLoading = animeViewModel.isLoading.collectAsState(initial = false)

    if (isLoading.value) {
        Log.d("AnimeScreen", "isLoading: ${isLoading.value}")
        DialogLoading {
            Column {
                CircularProgressIndicator()

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Loading"
                )
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = stringResource(id = R.string.anime))
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .consumeWindowInsets(paddingValues = paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    ),
                ),
        ) {

            LazyColumn {
                itemsIndexed(listAnime.value) { _, item ->
                    Text(text = item.synopsis ?: "Kukurruuu")
                }
            }

        }
    }

}