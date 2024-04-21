package com.example.composetask.core.presentation.base

interface PermissionCallBack {
    fun onPermissionGranted()

    fun onResultContainsDenied()
}