package com.example.composetask.compose

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.composetask.core.presentation.extension.showGenericAlertDialog
import com.example.composetask.core.presentation.extension.showToast

@Composable
fun HandleLoading(isLoading: Boolean) {
    if (isLoading) {
        LoadingDialog()
    }
}

@Composable
fun HandleError(code: String, message: String, context: Context) {
    context.showGenericAlertDialog(code = code.toInt(), message = message)
}

@Composable
fun ShowToast(context: Context, message: String, isConnectionError: Boolean) {
    context.showToast(message, isConnectionError)
}