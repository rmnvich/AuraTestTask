package com.aura.test.vromanovich.presentation.notification

import android.content.Context

interface BootNotificationManager {

    fun showNotification(body: String)

    companion object {

        fun from(context: Context): BootNotificationManager =
            BootNotificationManagerImpl(context)
    }
}