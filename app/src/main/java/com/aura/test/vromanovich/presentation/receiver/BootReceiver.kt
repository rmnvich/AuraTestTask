package com.aura.test.vromanovich.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.aura.test.vromanovich.domain.usecase.IsNotificationShownUseCase
import com.aura.test.vromanovich.presentation.notification.BootNotificationManager
import com.aura.test.vromanovich.presentation.worker.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var isNotificationShownUseCase: IsNotificationShownUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            // This is legal to use GlobalScope here, because this receiver lives while the process lives,
            // and it doesn't bounded with any lifecycle component, so leak is impossible here.
            @OptIn(DelicateCoroutinesApi::class)
            GlobalScope.launch {
                if (isNotificationShownUseCase()) {
                    showNotification(context)
                } else {
                    scheduleNotification(context)
                }
            }
        }
    }

    private fun showNotification(context: Context) {
        //TODO: Update logic of special body
        BootNotificationManager.from(context).showNotification("Special body from receiver")
    }

    private fun scheduleNotification(context: Context) {
        val notificationWork = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInitialDelay(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueue(notificationWork)
    }
}