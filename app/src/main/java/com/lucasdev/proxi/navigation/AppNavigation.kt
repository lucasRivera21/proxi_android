package com.lucasdev.proxi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasdev.proxi.auth.presentation.login.LoginScreen
import com.lucasdev.proxi.auth.presentation.register.RegisterScreen
import com.lucasdev.proxi.splash.presentation.SplashScreen

@Composable
fun AppNavigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = SplashRoute) {
        composable<SplashRoute> { SplashScreen() }
        composable<LoginRoute> { LoginScreen(paddingValues, navController) }
        composable<RegisterRoute> { RegisterScreen(paddingValues, navController) }
    }
}