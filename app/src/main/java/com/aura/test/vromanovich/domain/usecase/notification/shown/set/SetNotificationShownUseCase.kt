package com.aura.test.vromanovich.domain.usecase.notification.shown.set

interface SetNotificationShownUseCase {

    suspend operator fun invoke(isShown: Boolean)
}