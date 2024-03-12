package com.canwar.base.core.presentation.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogLoading(
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            Log.d("DialogLoading", "DialogLoading cannot be dismissed")
        },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        content()
    }
}