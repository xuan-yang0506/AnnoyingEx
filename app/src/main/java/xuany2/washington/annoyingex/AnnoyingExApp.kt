package xuany2.washington.annoyingex

import android.app.Application
import com.ericchee.arewethereyet.ExNotificationManager
import xuany2.washington.annoyingex.manager.ApiManager

class AnnoyingExApp: Application() {
    lateinit var apiManager: ApiManager
        private set
    lateinit var exNotificationManager: ExNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()
        apiManager = ApiManager(this)
        exNotificationManager = ExNotificationManager(this)
    }
}