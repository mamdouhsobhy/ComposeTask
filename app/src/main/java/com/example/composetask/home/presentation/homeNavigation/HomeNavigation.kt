package com.example.composetask.home.presentation.homeNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.home.presentation.homeDestination.MainScreen
import com.example.composetask.home.presentation.route.homeRoute

@Composable
fun HomeNavigation(sharedPrefs: SharedPrefs, isConnectedForMenu: Boolean) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreen.HomeScreen.route){
        homeRoute(navController,sharedPrefs,isConnectedForMenu)
    }
}