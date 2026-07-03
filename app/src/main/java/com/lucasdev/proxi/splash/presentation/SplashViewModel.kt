package com.lucasdev.proxi.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdev.proxi.navigation.LoginRoute
import com.lucasdev.proxi.navigation.MainRoute
import com.lucasdev.proxi.splash.data.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val splashRepository: SplashRepository) :
    ViewModel() {
    fun init(onNavigate: (Any) -> Unit) {
        viewModelScope.launch {
            val route = if (splashRepository.userLogged()) MainRoute else LoginRoute
            delay(500)
            onNavigate(route)
        }
    }
}