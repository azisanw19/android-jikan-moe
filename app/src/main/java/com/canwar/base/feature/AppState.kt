package com.canwar.base.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canwar.networkMonitor.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) : AppState {

    return remember(
        networkMonitor
    ) {
        AppState(
            networkMonitor = networkMonitor,
            coroutineScope = coroutineScope
        )
    }
}

class AppState(
    val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor
) {

    val isOffline = networkMonitor.isOnline
        .map { !it }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}