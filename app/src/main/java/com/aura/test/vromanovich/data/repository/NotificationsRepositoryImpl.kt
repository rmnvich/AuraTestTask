package com.aura.test.vromanovich.data.repository

import androidx.core.content.edit
import com.aura.test.vromanovich.data.storage.SharedPreferencesStorage
import com.aura.test.vromanovich.domain.repository.NotificationsRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class NotificationsRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage,
) : NotificationsRepository {

    private val sharedPreferences by lazy {
        sharedPreferencesStorage.get(NOTIFICATIONS_PREFERENCES_NAME)
    }

    override suspend fun isNotificationShown(): Boolean = suspendCancellableCoroutine {
        val isNotificationShown = sharedPreferences.getBoolean(IS_NOTIFICATION_SHOWN, false)
        it.resumeWith(Result.success(isNotificationShown))
    }

    override suspend fun setNotificationShown(isShown: Boolean) {
        suspendCancellableCoroutine {
            try {
                sharedPreferences.edit { putBoolean(IS_NOTIFICATION_SHOWN, isShown) }
                it.resumeWith(Result.success(Unit))
            } catch (ex: Exception) {
                it.resumeWithException(ex)
            }
        }
    }

    private companion object {
        const val NOTIFICATIONS_PREFERENCES_NAME = "Preferences.Notifications"

        const val IS_NOTIFICATION_SHOWN = "Key.IsNotificationShown"
    }
}