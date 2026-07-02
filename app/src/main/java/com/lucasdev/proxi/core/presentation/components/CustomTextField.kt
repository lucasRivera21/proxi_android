package com.lucasdev.proxi.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasdev.proxi.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String? = null,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var showPassword by remember { mutableStateOf(false) }
    val icon = if (showPassword) {
        painterResource(R.drawable.ic_eye)
    } else {
        painterResource(R.drawable.ic_eye_closed)
    }

    val visualTransformation = if (!showPassword && isPassword) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        label?.let {
            Text(
                label,
                color = colorResource(R.color.text_secondary),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        OutlinedTextField(
            value = value,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(errorMessage)
                }
            },
            onValueChange = onValueChange,
            maxLines = 1,
            modifier = modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(R.color.border),
                focusedBorderColor = colorResource(R.color.on_surface),
                focusedTextColor = colorResource(R.color.on_surface),
                unfocusedTextColor = colorResource(R.color.on_surface),
                cursorColor = colorResource(R.color.on_surface)
            ),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(placeholder, color = colorResource(R.color.text_secondary)) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = {
                        showPassword = !showPassword
                    }) {
                        Icon(
                            painter = icon,
                            tint = colorResource(R.color.text_secondary),
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}


@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(isPassword = true)
}