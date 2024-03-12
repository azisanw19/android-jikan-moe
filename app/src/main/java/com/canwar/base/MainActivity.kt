package com.canwar.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.canwar.base.core.presentation.theme.BaseTheme
import com.canwar.base.feature.BaseApp
import com.canwar.networkMonitor.NetworkManager
import com.canwar.networkMonitor.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val networkMonitor: NetworkMonitor by lazy {
        NetworkManager(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BaseTheme {
                BaseApp(
                    networkMonitor = networkMonitor,
                )
            }
        }
    }
}