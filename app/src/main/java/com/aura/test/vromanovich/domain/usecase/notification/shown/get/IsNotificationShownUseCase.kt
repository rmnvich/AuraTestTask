package com.aura.test.vromanovich.domain.usecase.notification.shown.get

interface IsNotificationShownUseCase {

    suspend operator fun invoke(): Boolean
}