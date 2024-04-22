package com.example.composetask.home.presentation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.home.presentation.home.HomeScreen
import com.example.composetask.home.presentation.homeDestination.MainScreen

fun NavGraphBuilder.homeRoute(
    navController: NavController,
    sharedPrefs: SharedPrefs,
    isConnectedForMenu: Boolean
){
    composable(
        MainScreen.HomeScreen.route
    ){ HomeScreen(sharedPrefs, isConnectedForMenu = isConnectedForMenu) }
}
