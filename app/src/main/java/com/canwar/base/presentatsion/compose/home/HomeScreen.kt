package com.canwar.base.presentatsion.compose.home

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.canwar.base.R
import com.canwar.base.presentatsion.AppState
import com.canwar.base.presentatsion.theme.BaseTheme
import com.canwar.base.utils.previews.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    appState: AppState,
    homeViewModel: HomeViewModel = viewModel(),
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    when (homeState) {
        is HomeState.NavigateTo -> {

        }

        else -> {

        }
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
            HomeScreenTopBar()
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
                    homeViewModel.setActionEvent(
                        HomeEvent.NavigateTo(
                            homeScreenDestinations = homeScreenDestinations
                        )
                    )
                }
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


        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.home_screen))
        }
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
) {
    NavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { homeScreenDestinations ->
            NavigationBarItem(
                selected = false,
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