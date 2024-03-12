package com.canwar.base.feature.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.canwar.base.R
import com.canwar.base.feature.home.anime.presentation.ANIME_ROUTE
import com.canwar.base.feature.home.anime.presentation.AnimeScreen
import com.canwar.base.feature.home.manga.presentation.MANGA_ROUTE
import com.canwar.base.feature.home.manga.presentation.MangaScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNavigation(
    homeNavHostController: NavHostController,
) {
    val stateHomeScreenDestinations = remember {
        mutableStateOf(HomeScreenDestinations.Anime)
    }

    Scaffold(
        bottomBar = {
            HomeBottomNavigationBar(
                destinations = listOf(
                    HomeScreenDestinations.Anime,
                    HomeScreenDestinations.Manga,
                ),
                onClick = { homeScreenDestinations ->
                    stateHomeScreenDestinations.value = homeScreenDestinations
                    homeNavHostController.navigate(homeScreenDestinations.route)
                },
                selectedDestinations = stateHomeScreenDestinations.value,
            )
        }
    ) {
        NavHost(
            navController = homeNavHostController,
            startDestination = ANIME_ROUTE,
        ) {

            composable(route = ANIME_ROUTE) {
                AnimeScreen()
            }

            composable(route = MANGA_ROUTE) {
                MangaScreen()
            }

        }
    }
}

enum class HomeScreenDestinations(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
    val route: String,
) {
    Anime(
        selectedIcon = Icons.Rounded.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        iconTextId = R.string.anime,
        titleTextId = R.string.anime,
        route = ANIME_ROUTE,
    ),
    Manga(
        selectedIcon = Icons.Rounded.Face,
        unselectedIcon = Icons.Outlined.Face,
        iconTextId = R.string.manga,
        titleTextId = R.string.manga,
        route = MANGA_ROUTE
    ),
}

@Composable
fun HomeBottomNavigationBar(
    destinations: List<HomeScreenDestinations>,
    modifier: Modifier = Modifier,
    onClick: (homeScreenDestinations: HomeScreenDestinations) -> Unit,
    selectedDestinations: HomeScreenDestinations
) {
    NavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { homeScreenDestinations ->
            NavigationBarItem(
                selected = selectedDestinations == homeScreenDestinations,
                onClick = {
                    onClick(homeScreenDestinations)
                },
                icon = {
                    Icon(
                        imageVector = homeScreenDestinations.unselectedIcon,
                        contentDescription = stringResource(id = homeScreenDestinations.iconTextId),
                    )
                },
                label = {
                    Text(text = stringResource(id = homeScreenDestinations.titleTextId))
                },
            )
        }
    }
}