package com.example.composetask.authentication.presentation.login

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetask.R
import com.example.composetask.authentication.presentation.authDestination.AuthScreen
import com.example.composetask.authentication.presentation.login.validate.LoginRequest
import com.example.composetask.authentication.presentation.login.validate.LoginValidation
import com.example.composetask.authentication.presentation.login.viewmodel.LoginViewModel
import com.example.composetask.compose.HandleLoading
import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.compose.TitleAndInput
import com.example.composetask.ui.theme.PURPLE3

@Composable
fun LoginScreen(
    navController: NavController,
    sharedPrefs: SharedPrefs,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val validateState by loginViewModel.validateState.collectAsState()
    val loginRequestState by loginViewModel.loginRequestState.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()
    val changeLoginHappened by loginViewModel.changeLoginHappened.collectAsState()

    val context = LocalContext.current

    if (changeLoginHappened) {
        handleStateChange(loginState, context, sharedPrefs, navController)
    }

    LoginContent(validateState, loginRequestState, onEmailValueChanged = { email ->
        loginViewModel.onEmailValueChanged(email)
    }, onPasswordValueChanged = { password ->
        loginViewModel.onPasswordValueChanged(password)
    }, validateInput = {
        loginViewModel.validateForm(loginRequestState.emailOrUserName, loginRequestState.password)
    }) {
        loginViewModel.makeLogin()
    }
}

//region handleStateChange()
@Composable
private fun handleStateChange(
    loginState: BaseState<LoginRequest>,
    context: Context,
    sharedPrefs: SharedPrefs,
    navController: NavController
) {
    when (loginState) {
        is BaseState.Init -> Unit
        is BaseState.IsLoading -> HandleLoading(loginState.isLoading)
        is BaseState.Success -> handleSuccess(loginState.items, sharedPrefs, context, navController)
        else -> Unit
    }

}

@Composable
private fun handleSuccess(
    data: LoginRequest?,
    sharedPrefs: SharedPrefs,
    context: Context,
    navController: NavController
) {
    sharedPrefs.saveUser(data)
    navController.navigate(AuthScreen.FinishAuthScreen.route)
}

//endregion

@Composable
private fun LoginContent(
    validateState: LoginValidation,
    loginRequestState: LoginRequest,
    onEmailValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    validateInput: () -> Boolean,
    makeLogin: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 25.dp, horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {

        var passwordState by remember { mutableStateOf(false) }

        Text(
            text = "Login to your account",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        TitleAndInput(
            title = "email or username",
            errorTitle = if (validateState.isEmailEmpty) {
                "please enter your email"
            } else {
                ""
            },
            hint = "enter your email",
            keyboardType = KeyboardType.Email,
            showInputUser = true,
            painter = painterResource(id = R.drawable.ic_email),
            spacer = 25.dp,
            value = loginRequestState.emailOrUserName,
            isError = validateState.isEmailEmpty,
            isEmail = true,
            onValueChange = {
                onEmailValueChanged(it)
            }
        )

        TitleAndInput(
            title = "password",
            errorTitle = "please enter your password",
            hint = "enter your password",
            keyboardType = KeyboardType.Password,
            showInputUser = passwordState,
            showTrailingIcon = true,
            painter = null,
            spacer = 15.dp,
            value = loginRequestState.password,
            isError = validateState.isPasswordEmpty,
            onToggleClicked = {
                passwordState = !passwordState
            },
            onValueChange = {
                onPasswordValueChanged(it)
            }
        )

        Button(
            onClick = {
                if (validateInput()) {
                    makeLogin()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(contentColor = PURPLE3)
        ) {
            Text(
                text = "login",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }

    }

}
