package xuany2.washington.annoyingex

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import xuany2.washington.annoyingex.manager.ExNotificationManager
import kotlin.random.Random

class SendAMessageWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    lateinit var exNotificationManager: ExNotificationManager

    override fun doWork(): Result {
        (context as AnnoyingExApp).allMessage?.let { all ->
            val index: Int = Random.nextInt(0, all.messages.size)
            val message: String = all.messages[index]
            (context as AnnoyingExApp).exNotificationManager.sendNewMessage(message)
            Log.i("tag", "$index")
        }

        return Result.success()
    }
}