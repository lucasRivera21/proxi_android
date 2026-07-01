package com.lucasdev.proxi.splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.lucasdev.proxi.R

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary)), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(R.color.on_primary),
            modifier = Modifier.size(48.dp)
        )
    }
}