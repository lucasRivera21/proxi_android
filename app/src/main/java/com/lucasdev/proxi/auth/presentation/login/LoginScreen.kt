package com.lucasdev.proxi.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lucasdev.proxi.R
import com.lucasdev.proxi.auth.domain.model.LoginModel
import com.lucasdev.proxi.auth.domain.model.error.LoginCredentialsError
import com.lucasdev.proxi.core.presentation.components.CustomBanner
import com.lucasdev.proxi.core.presentation.components.CustomButton
import com.lucasdev.proxi.core.presentation.components.CustomTextField
import com.lucasdev.proxi.navigation.LoginRoute
import com.lucasdev.proxi.navigation.MainRoute
import com.lucasdev.proxi.navigation.RegisterRoute

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    vm: LoginViewModel
) {
    val loginModel by vm.loginModel.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(R.color.surface))
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .background(
                        colorResource(R.color.primary_container),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_map_pin),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp),
                    tint = colorResource(R.color.primary)
                )
            }
        }

        item {
            Header()
        }

        loginModel.error?.let { error ->
            item {
                CustomBanner(
                    icon = painterResource(R.drawable.ic_triangle_alert),
                    title = stringResource(
                        if (error is LoginCredentialsError) R.string.login_invalid_credentials else
                            R.string.unknown_error
                    ),
                    description = stringResource(
                        if (error is LoginCredentialsError) R.string.login_verify_credentials else
                            R.string.try_again_later
                    )
                )
            }
        }

        item { Form(loginModel, vm, navController) }

        item {
            Footer(navController)
        }
    }
}

@Composable
fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            stringResource(R.string.app_name),
            color = colorResource(R.color.on_surface),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            stringResource(R.string.title_login),
            color = colorResource(R.color.text_secondary)
        )
    }
}

@Composable
fun Form(loginModel: LoginModel, vm: LoginViewModel, navController: NavHostController) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        CustomTextField(
            value = loginModel.email,
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email_placeholder),
            keyboardType = KeyboardType.Email,
            isError = !loginModel.isEmailValid,
            errorMessage = stringResource(R.string.email_error)
        ) {
            vm.onEmailChange(it)
        }
        CustomTextField(
            value = loginModel.password,
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            isError = !loginModel.isPasswordValid,
            errorMessage = stringResource(R.string.password_error),
            placeholder = "••••••••",
            isPassword = true
        ) {
            vm.onPasswordChange(it)
        }
        CustomButton(
            text = stringResource(R.string.login_button),
            isLoading = loginModel.isLoading
        ) {
            vm.onLoginClick {
                navController.navigate(MainRoute) {
                    popUpTo(LoginRoute) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
fun Footer(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.login_footer), color = colorResource(R.color.text_secondary))
        Text(
            stringResource(R.string.login_footer_link),
            color = colorResource(R.color.primary),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate(RegisterRoute)
            }
        )
    }
}