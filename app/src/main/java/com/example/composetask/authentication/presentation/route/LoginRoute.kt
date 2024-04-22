package com.example.composetask.authentication.presentation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composetask.authentication.presentation.authDestination.AuthScreen
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.authentication.presentation.login.LoginScreen

fun NavGraphBuilder.loginRoute(navController: NavController,sharedPrefs: SharedPrefs){
    composable(
        AuthScreen.LoginScreen.route
    ){ LoginScreen(navController,sharedPrefs) }
}
