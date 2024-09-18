package com.aura.test.vromanovich.presentation.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aura.test.vromanovich.presentation.notification.BootNotificationManager

class NotificationWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {

    private val bootNotificationManager by lazy {
        BootNotificationManager.from(context)
    }

    override fun doWork(): Result {
        //TODO: Update info about special body
        bootNotificationManager.showNotification("Special body from worker")
        return Result.success()
    }
}