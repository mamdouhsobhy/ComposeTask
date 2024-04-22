package com.example.composetask.home.presentation.homeDestination

sealed class MainScreen(val route : String) {
    object HomeScreen : MainScreen("HomeScreen")

}