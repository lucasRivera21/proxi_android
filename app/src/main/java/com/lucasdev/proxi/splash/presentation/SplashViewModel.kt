package com.lucasdev.proxi.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    fun init(onNavigate: () -> Unit) {
        viewModelScope.launch {
            delay(3000)
            onNavigate()
        }
    }
}