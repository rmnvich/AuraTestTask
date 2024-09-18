package com.aura.test.vromanovich.domain.usecase

import com.aura.test.vromanovich.domain.repository.NotificationsRepository
import javax.inject.Inject

class IsNotificationShownUseCaseImpl @Inject constructor(
    private val notificationsRepository: NotificationsRepository,
) : IsNotificationShownUseCase {

    override suspend fun invoke(): Boolean = notificationsRepository.isNotificationShown()
}