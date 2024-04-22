package com.example.composetask.authentication.presentation.login.validate

data class LoginValidation(
    val isEmailEmpty: Boolean = false,
    val isPasswordEmpty: Boolean = false,
)

data class LoginRequest(
    val emailOrUserName: String = "",
    val password: String = ""
)