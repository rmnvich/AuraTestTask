package com.aura.test.vromanovich.di

import com.aura.test.vromanovich.domain.usecase.notification.shown.get.IsNotificationShownUseCase
import com.aura.test.vromanovich.domain.usecase.notification.shown.get.IsNotificationShownUseCaseImpl
import com.aura.test.vromanovich.domain.usecase.notification.shown.set.SetNotificationShownUseCase
import com.aura.test.vromanovich.domain.usecase.notification.shown.set.SetNotificationShownUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NotificationUseCasesModule {

    @Binds
    fun bindIsNotificationShownUseCase(impl: IsNotificationShownUseCaseImpl): IsNotificationShownUseCase

    @Binds
    fun bindSetNotificationShownUseCase(impl: SetNotificationShownUseCaseImpl): SetNotificationShownUseCase
}