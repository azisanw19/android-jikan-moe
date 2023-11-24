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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.canwar.base.R
import com.canwar.base.presentatsion.components.CenterTopBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onButtonToProfileClicked: () -> Unit,
) {

    Scaffold { paddingValues: PaddingValues ->
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
            CenterTopBar(
                titleRes = R.string.home_screen,
                navigationIcon = Icons.Rounded.Search,
                navigationIconContentDescription = null,
                actions = {
                    IconButton(onClick = { /* TODO doSomething() */ }) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    val badgeNumber = "8"
                                    Text(
                                        badgeNumber,
                                        modifier = Modifier.semantics {
                                            contentDescription = "$badgeNumber new notifications"
                                        }
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                    IconButton(onClick = onButtonToProfileClicked) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
            Text(text = "Home Screen")
            Button(onClick = onButtonToProfileClicked) {
                Text(text = "Go to Profile")
            }
        }

    }

}