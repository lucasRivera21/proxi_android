package com.lucasdev.proxi.splash.di

import com.lucasdev.proxi.splash.data.repository.SplashRepository
import com.lucasdev.proxi.splash.data.repository.SplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SplashRepositoryModule {
    @Binds
    abstract fun bindSplashRepository(impl: SplashRepositoryImpl): SplashRepository
}