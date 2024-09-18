package com.aura.test.vromanovich.data.repository

import androidx.core.content.edit
import com.aura.test.vromanovich.data.storage.SharedPreferencesStorage
import com.aura.test.vromanovich.domain.repository.BootsRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class BootsRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage,
) : BootsRepository {

    private val sharedPreferences by lazy {
        sharedPreferencesStorage.get(BOOTS_PREFERENCES_NAME)
    }

    override suspend fun setLastBootTime(timestamp: Long) {
        suspendCancellableCoroutine {
            try {
                sharedPreferences.edit { putLong(KEY_LAST_BOOT_TIMESTAMP, timestamp) }
                it.resumeWith(Result.success(Unit))
            } catch (ex: Exception) {
                it.resumeWithException(ex)
            }
        }
    }

    override suspend fun getLastBootTime(): Long {
        return suspendCancellableCoroutine {
            val timestamp = sharedPreferences.getLong(KEY_LAST_BOOT_TIMESTAMP, -1)
            it.resumeWith(Result.success(timestamp))
        }
    }

    private companion object {
        const val BOOTS_PREFERENCES_NAME = "Preferences.Boots"

        const val KEY_LAST_BOOT_TIMESTAMP = "Key.LastBootTimestamp"
    }
}