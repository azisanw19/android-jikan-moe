package com.canwar.base.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.canwar.base.R
import com.canwar.base.feature.home.HomeNavigation
import com.canwar.networkMonitor.NetworkMonitor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseApp(
    networkMonitor: NetworkMonitor,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor,
    )
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackBarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        }
    ) {
        AppNavigation()
    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
) {
    val navHostController: NavHostController = rememberNavController()
    val homeNavHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = "home",
        modifier = modifier,
    ) {
        composable(route = "home") {
            HomeNavigation(
                homeNavHostController = homeNavHostController,
            )
        }
    }
}