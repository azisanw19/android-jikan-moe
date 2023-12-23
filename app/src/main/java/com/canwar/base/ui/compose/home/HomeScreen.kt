package com.canwar.base.ui.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.canwar.base.R
import com.canwar.base.ui.AppState
import com.canwar.base.ui.compose.anime.AnimePage
import com.canwar.base.ui.theme.BaseTheme
import com.canwar.base.utils.previews.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    appState: AppState,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val stateHomeScreenDestinations = remember {
        mutableStateOf(HomeScreenDestinations.Anime)
    }

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
        topBar = {
            if (stateHomeScreenDestinations.value == HomeScreenDestinations.Anime) {
                HomeScreenTopBar(
                    title = {
                        Text(text = stringResource(id = R.string.anime))
                    }
                )
            } else if (stateHomeScreenDestinations.value == HomeScreenDestinations.Manga) {
                HomeScreenTopBar(
                    title = {
                        Text(text = stringResource(id = R.string.manga))
                    }
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        },
        bottomBar = {
            HomeBottomNavigationBar(
                destinations = listOf(
                    HomeScreenDestinations.Anime,
                    HomeScreenDestinations.Manga,
                ),
                onClick = { homeScreenDestinations ->
                    stateHomeScreenDestinations.value = homeScreenDestinations
                },
                selectedDestinations = stateHomeScreenDestinations.value,
            )
        }
    ) { paddingValues: PaddingValues ->
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
            AnimePage(
                appState = appState,
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title
    )
}

enum class HomeScreenDestinations(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    Anime(
        selectedIcon = Icons.Rounded.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        iconTextId = R.string.anime,
        titleTextId = R.string.anime,
    ),
    Manga(
        selectedIcon = Icons.Rounded.Face,
        unselectedIcon = Icons.Outlined.Face,
        iconTextId = R.string.manga,
        titleTextId = R.string.manga,
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

@ThemePreviews
@Composable
fun PreviewHomeBottomNavigationBar() {

    BaseTheme {
        HomeBottomNavigationBar(
            destinations = listOf(
                HomeScreenDestinations.Anime,
                HomeScreenDestinations.Manga,
            ),
            onClick = {},
            selectedDestinations = HomeScreenDestinations.Anime,
        )
    }
}


@ThemePreviews
@Composable
fun PreviewHomeScreenTopBar() {
    BaseTheme {
        HomeScreenTopBar()
    }
}