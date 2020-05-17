package xuany2.washington.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import xuany2.washington.annoyingex.manager.ApiManager
import xuany2.washington.annoyingex.model.AllMessage
import kotlin.random.Random

class SendAMessageWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createFunChannel()
    }

    override fun doWork(): Result {
        ApiManager(context).fetchSentences({all -> sendNewMessage(all)}, {sendOneMessage("unable to retrieve message")})

        return Result.success()

    }

    fun sendOneMessage(message: String) {
        val intent = Intent(context, MainActivity::class.java)

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sms_black_24dp)
            .setContentTitle("Donald Trump")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    fun sendNewMessage(allMessage: AllMessage) {

        val index = Random.nextInt(0, allMessage.messages.size)
        val message = allMessage.messages[index]
        sendOneMessage(message)

    }

    private fun createFunChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Annoying Notifications"
            val descriptionText = "Some annoying message from an ex"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }
}