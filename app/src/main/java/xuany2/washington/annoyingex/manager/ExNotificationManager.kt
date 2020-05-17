package xuany2.washington.annoyingex.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import xuany2.washington.annoyingex.MainActivity
import xuany2.washington.annoyingex.R
import kotlin.random.Random

class ExNotificationManager(
    private val context: Context
) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createFunChannel()
    }

    fun sendNewMessage(message: String) {
        val intent = Intent(context, MainActivity::class.java)

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sms_black_24dp)
            .setContentTitle("New message from ex")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createFunChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Fun Notifications"
            val descriptionText = "All Msgs from a great autotune voiced dude"
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