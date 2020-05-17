package xuany2.washington.annoyingex

import android.app.Application
import xuany2.washington.annoyingex.manager.ApiManager
import xuany2.washington.annoyingex.manager.BackgroundWorkManager
import xuany2.washington.annoyingex.manager.ExNotificationManager
import xuany2.washington.annoyingex.model.AllMessage

class AnnoyingExApp: Application() {
    lateinit var apiManager: ApiManager
        private set
    lateinit var backgroundWorkManager: BackgroundWorkManager
        private set

    lateinit var exNotificationManager: ExNotificationManager

    var allMessage: AllMessage?= null

    override fun onCreate() {
        super.onCreate()
        apiManager = ApiManager(this)
        backgroundWorkManager = BackgroundWorkManager(this)
        exNotificationManager = ExNotificationManager(this)
    }

    fun fillMessages(messages: AllMessage) {
        allMessage = messages
    }
}