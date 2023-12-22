package com.canwar.base.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.canwar.base.ui.AppState
import com.canwar.base.ui.compose.home.HomeScreen
import com.canwar.base.ui.compose.profile.ProfileScreen
import com.canwar.base.ui.rememberAppState
import com.canwar.networkMonitor.NetworkMonitor

@Composable
fun BaseApp(
    networkMonitor: NetworkMonitor,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor,
    )
) {
    BaseNavHost(
        appState = appState
    )
}

@Composable
fun BaseNavHost(
    appState: AppState
) {
    NavHost(
        navController = appState.navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            HomeScreen(
                appState = appState,
            )
        }

        composable(route = "profile") {
            ProfileScreen(
                appState = appState,
            )
        }
    }
}