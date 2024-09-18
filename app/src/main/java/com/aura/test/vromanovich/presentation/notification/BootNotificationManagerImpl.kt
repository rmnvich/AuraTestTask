package com.aura.test.vromanovich.presentation.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.aura.test.vromanovich.R

class BootNotificationManagerImpl(
    private val context: Context,
) : BootNotificationManager {

    private val notificationManager: NotificationManager? by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
    }

    override fun showNotification(body: String) {
        val notification = NotificationCompat.Builder(context, BOOTS_INFORMATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(R.string.notification_boots_title))
            .setContentText(body)
            .build()

        notificationManager?.notify(BOOTS_INFORMATION_NOTIFICATION_ID, notification)
    }

    private companion object {
        const val BOOTS_INFORMATION_CHANNEL_ID = "boot_info_channel_id"

        const val BOOTS_INFORMATION_NOTIFICATION_ID = 2000
    }
}