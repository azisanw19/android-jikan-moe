package com.canwar.base.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogLoading(
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {

        CircularProgressIndicator()

    }
}