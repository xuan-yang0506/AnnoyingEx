package xuany2.washington.annoyingex.manager


import android.content.Context
import androidx.work.*
import xuany2.washington.annoyingex.SendAMessageWorker
import java.util.concurrent.TimeUnit

class BackgroundWorkManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startSendingMessages() {
        if (isRunning()) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<SendAMessageWorker>(15, TimeUnit.MINUTES)
            .setInitialDelay(5000, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .addTag(WORK_REQUEST_TAG)
            .build()


        workManager.enqueue(workRequest)

    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(WORK_REQUEST_TAG)
    }


    companion object {
        const val WORK_REQUEST_TAG = "AWTY_WORK_REQUEST_TAG"
    }

}