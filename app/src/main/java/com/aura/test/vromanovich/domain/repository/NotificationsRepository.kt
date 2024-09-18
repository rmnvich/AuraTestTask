package com.aura.test.vromanovich.domain.repository

interface NotificationsRepository {

    suspend fun isNotificationShown(): Boolean

    suspend fun setNotificationShown(isShown: Boolean)
}