package com.aura.test.vromanovich.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.aura.test.vromanovich.domain.usecase.boots.last.set.SetLastBootTimestampUseCase
import com.aura.test.vromanovich.domain.usecase.notification.shown.get.IsNotificationShownUseCase
import com.aura.test.vromanovich.domain.usecase.notification.shown.set.SetNotificationShownUseCase
import com.aura.test.vromanovich.presentation.notification.BootNotificationManager
import com.aura.test.vromanovich.presentation.worker.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var isNotificationShownUseCase: IsNotificationShownUseCase

    @Inject
    lateinit var setNotificationShownUseCase: SetNotificationShownUseCase

    @Inject
    lateinit var setLastBootTimestampUseCase: SetLastBootTimestampUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            // This is legal to use GlobalScope here, because this receiver lives while the process lives,
            // and it doesn't bounded with any lifecycle component, so leak is impossible here.
            @OptIn(DelicateCoroutinesApi::class)
            GlobalScope.launch(Dispatchers.IO) {
                //TODO: Better provide interface with dispatchers using DI.
                val timestamp = System.currentTimeMillis()
                //TODO: Implement saving of many boots, not only the last one
                setLastBootTimestampUseCase(timestamp)

                val isNotificationShown = isNotificationShownUseCase()
                withContext(Dispatchers.Main) {
                    if (isNotificationShown) {
                        showNotification(context)
                    } else {
                        scheduleNotification(context)
                    }
                }
            }
        }
    }

    private suspend fun showNotification(context: Context) {
        //TODO: Update logic of special body
        BootNotificationManager.from(context)
            .showNotification("Special body from receiver")

        withContext(Dispatchers.IO) {
            setNotificationShownUseCase(true)
        }
    }

    //TODO: Implement rescheduling work
    private suspend fun scheduleNotification(context: Context) {
        val notificationWorkId = UUID.randomUUID()
        val notificationWork = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInitialDelay(15, TimeUnit.MINUTES)
            .setId(notificationWorkId)
            .build()

        WorkManager.getInstance(context).run {
            enqueue(notificationWork)
            getWorkInfoByIdFlow(notificationWorkId)
                .filter { it.state == WorkInfo.State.SUCCEEDED }
                .collect { showNotification(context) }
        }
    }
}