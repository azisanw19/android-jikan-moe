package com.canwar.base.feature.home.manga.presentation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

const val MANGA_ROUTE = "manga-route"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MangaScreen() {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Manga")
                }
            )
        }
    ) { paddingValues: PaddingValues ->
        Row(
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
            Text(text = "Manga Screen")
        }
    }


}