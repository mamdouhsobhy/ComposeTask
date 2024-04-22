package com.example.composetask.authentication.presentation.authDestination

sealed class AuthScreen(val route : String) {
    object LoginScreen : AuthScreen("LoginScreen")
    object FinishAuthScreen : AuthScreen("FinishAuthScreen")

}