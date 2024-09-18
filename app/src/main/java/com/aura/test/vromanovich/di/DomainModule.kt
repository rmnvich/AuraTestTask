package com.aura.test.vromanovich.di

import com.aura.test.vromanovich.domain.usecase.IsNotificationShownUseCase
import com.aura.test.vromanovich.domain.usecase.IsNotificationShownUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindIsNotificationShownUseCase(impl: IsNotificationShownUseCaseImpl): IsNotificationShownUseCase
}