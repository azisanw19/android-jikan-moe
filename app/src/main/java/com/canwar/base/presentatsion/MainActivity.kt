package com.canwar.base.presentatsion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.canwar.base.presentatsion.compose.BaseApp
import com.canwar.base.presentatsion.theme.BaseTheme
import com.canwar.networkMonitor.NetworkManager
import com.canwar.networkMonitor.NetworkMonitor

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