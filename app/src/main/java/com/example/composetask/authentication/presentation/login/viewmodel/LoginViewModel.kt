package com.example.composetask.authentication.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composetask.authentication.presentation.login.validate.LoginRequest
import com.example.composetask.authentication.presentation.login.validate.LoginValidation
import com.example.composetask.core.presentation.base.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _validateState = MutableStateFlow(LoginValidation())
    val validateState = _validateState.asStateFlow()

    private val _loginRequestState = MutableStateFlow(LoginRequest())
    val loginRequestState = _loginRequestState.asStateFlow()

    private val _loginState = MutableStateFlow<BaseState<LoginRequest>>(BaseState.Init)
    val loginState = _loginState.asStateFlow()

    private val _changeLoginHappened = MutableStateFlow(false)
    var changeLoginHappened = _changeLoginHappened.asStateFlow()

    fun validateForm(email: String, password: String): Boolean {
        return if (email.isEmpty()) {
            _validateState.update { it.copy(isEmailEmpty = true) }
            false
        } else if (password.isEmpty()) {
            _validateState.update { it.copy(isPasswordEmpty = true) }
            false
        } else {
            true
        }
    }

    fun onEmailValueChanged(email: String) {
        _loginRequestState.update { it.copy(emailOrUserName = email) }
        _validateState.update { it.copy(isEmailEmpty = false) }
        _changeLoginHappened.value = false
    }

    fun onPasswordValueChanged(password: String) {
        _loginRequestState.update { it.copy(password = password) }
        _validateState.update { it.copy(isPasswordEmpty = false) }
        _changeLoginHappened.value = false
    }

    fun makeLogin() {
        _changeLoginHappened.value = true
        _loginState.value = BaseState.Success(
            LoginRequest(
                _loginRequestState.value.emailOrUserName,
                _loginRequestState.value.password
            )
        )
    }

}
