package com.lucasdev.proxi.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasdev.proxi.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    bgColor: Color = colorResource(R.color.primary),
    textColor: Color = colorResource(R.color.on_primary),
    text: String = "",
    icon: Painter? = null,
    onClick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        border = null,
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            icon?.let { Icon(icon, contentDescription = null) }
            Text(text, fontWeight = FontWeight.Bold, color = textColor)
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(text = "Iniciar sesión")
}