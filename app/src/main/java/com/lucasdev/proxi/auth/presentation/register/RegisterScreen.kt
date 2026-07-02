package com.lucasdev.proxi.auth.presentation.register

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
import com.lucasdev.proxi.core.presentation.components.CustomButton
import com.lucasdev.proxi.core.presentation.components.CustomTextField

@Composable
fun RegisterScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    vm: RegisterViewModel
) {
    val name by vm.name.collectAsState()
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.surface))
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                    painter = painterResource(R.drawable.ic_user_plus),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp),
                    tint = colorResource(R.color.primary)
                )
            }
        }

        item {
            Header()
        }

        item { Form(name, email, password, vm) }

        item { Footer(navController) }
    }
}

@Composable
fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.register_button),
            color = colorResource(R.color.on_surface),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            stringResource(R.string.register_title),
            color = colorResource(R.color.text_secondary)
        )
    }
}

@Composable
fun Form(name: String, email: String, password: String, vm: RegisterViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        CustomTextField(
            value = name,
            label = stringResource(R.string.name),
            placeholder = stringResource(R.string.name)
        ) {
            vm.onNameChange(it)
        }
        CustomTextField(
            value = email,
            keyboardType = KeyboardType.Email,
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email_placeholder)
        ) {
            vm.onEmailChange(it)
        }
        CustomTextField(
            value = password,
            keyboardType = KeyboardType.Password,
            label = stringResource(R.string.password),
            placeholder = "••••••••",
            isPassword = true
        ) {
            vm.onPasswordChange(it)
        }

        CustomButton(text = stringResource(R.string.register_button)) { }
    }
}

@Composable
fun Footer(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.register_footer),
            color = colorResource(R.color.text_secondary)
        )
        Text(
            stringResource(R.string.register_footer_link),
            color = colorResource(R.color.primary),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
    }
}