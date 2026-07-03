package com.lucasdev.proxi.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasdev.proxi.R

@Composable
fun CustomBanner(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    title: String? = null,
    description: String? = null,
    containerColor: Color = colorResource(R.color.error_container),
    textColor: Color = colorResource(R.color.error),
    iconColor: Color = colorResource(R.color.on_error)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor, shape = RoundedCornerShape(12.dp))
            .border(1.dp, textColor, RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Box(
                modifier = Modifier
                    .background(textColor, shape = CircleShape)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = iconColor
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            title?.let {
                Text(
                    title,
                    fontWeight = FontWeight.ExtraBold,
                    color = textColor
                )
            }
            description?.let {
                Text(
                    description,
                    fontSize = 12.sp,
                    color = colorResource(R.color.on_surface)
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomBannerPreview() {
    CustomBanner(
        icon = painterResource(R.drawable.ic_user_plus),
        title = "Formato no permitido",
        description = "Selecciona un archivo JPG, PNG o PDF para continuar."
    )
}