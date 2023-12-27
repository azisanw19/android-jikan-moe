package com.canwar.base.ui.compose.anime

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.canwar.base.ui.AppState
import com.canwar.base.ui.components.DialogLoading

@Composable
fun AnimePage(
    appState: AppState,
    animeViewModel: AnimeViewModel = hiltViewModel()
) {
    animeViewModel.getAnime()
    val listAnime = animeViewModel.data.collectAsState(initial = listOf())
    val isLoading = animeViewModel.isLoading.collectAsState(initial = true)

    if (isLoading.value) {
        DialogLoading(onDismiss = {})
    }

    LazyColumn {
        itemsIndexed(listAnime.value) { _, item ->
            Text(text = item.synopsis ?: "Kukurruuu")
            Button(
                onClick = {
                    appState.navController.navigate("profile")
                },
            ) {
                Text(text = "Go to profile")
            }
        }
    }

}