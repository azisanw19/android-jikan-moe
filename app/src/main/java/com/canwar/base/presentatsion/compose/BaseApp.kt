package com.canwar.base.presentatsion.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.canwar.base.presentatsion.compose.home.HomeScreen
import com.canwar.base.presentatsion.compose.profile.ProfileScreen
import com.canwar.networkMonitor.NetworkMonitor

@Composable
fun BaseApp(
    networkMonitor: NetworkMonitor,
) {
    // Setup navigation Here
    val navController = rememberNavController()
    BaseNavHost(
        navController = navController,
    )
}

@Composable
fun BaseNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            HomeScreen(
                onButtonToProfileClicked = {
                    navController.navigate("profile")
                },
            )
        }

        composable(route = "profile") {
            ProfileScreen(

            )
        }
    }
}