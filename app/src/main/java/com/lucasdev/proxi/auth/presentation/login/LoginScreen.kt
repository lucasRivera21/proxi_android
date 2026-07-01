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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasdev.proxi.R
import com.lucasdev.proxi.core.presentation.components.CustomButton
import com.lucasdev.proxi.core.presentation.components.CustomTextField

@Composable
fun LoginScreen(paddingValues: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(R.color.surface))
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
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

        Header()

        Form()

        Footer()
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
fun Form() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        CustomTextField(
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email_placeholder)
        ) {}
        CustomTextField(
            label = stringResource(R.string.password),
            placeholder = "••••••••",
            isPassword = true
        ) {}
        CustomButton(text = stringResource(R.string.login_button)) {}
    }
}

@Composable
fun Footer() {
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

            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(PaddingValues())
}