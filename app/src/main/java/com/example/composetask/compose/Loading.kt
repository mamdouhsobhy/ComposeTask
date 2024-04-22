package com.example.composetask.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { }) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }
}
