package com.example.composetask.authentication.presentation.authNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.authentication.presentation.authDestination.AuthScreen
import com.example.composetask.authentication.presentation.route.loginRoute

@Composable
fun AuthNavigation(sharedPrefs: SharedPrefs, finishAuthScreen:()->Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AuthScreen.LoginScreen.route){
        loginRoute(navController,sharedPrefs)
        composable(AuthScreen.FinishAuthScreen.route){
            finishAuthScreen()
        }
    }
}