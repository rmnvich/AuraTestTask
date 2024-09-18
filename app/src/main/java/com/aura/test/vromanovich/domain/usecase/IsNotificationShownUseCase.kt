package com.aura.test.vromanovich.domain.usecase

interface IsNotificationShownUseCase {

    suspend operator fun invoke(): Boolean
}