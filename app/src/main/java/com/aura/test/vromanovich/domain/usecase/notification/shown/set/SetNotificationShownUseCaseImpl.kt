package com.aura.test.vromanovich.domain.usecase.notification.shown.set

import com.aura.test.vromanovich.domain.repository.NotificationsRepository
import javax.inject.Inject

class SetNotificationShownUseCaseImpl @Inject constructor(
    private val notificationsRepository: NotificationsRepository,
) : SetNotificationShownUseCase {

    override suspend fun invoke(isShown: Boolean) {
        notificationsRepository.setNotificationShown(isShown)
    }
}