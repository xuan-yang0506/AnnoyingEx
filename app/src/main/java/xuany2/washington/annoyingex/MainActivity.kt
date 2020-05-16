package xuany2.washington.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import xuany2.washington.annoyingex.model.AllMessage
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var allMessage: AllMessage ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as AnnoyingExApp).apiManager.fetchSentences(
            {all -> fillMessage(all)}, { Toast.makeText(this, "fetch error", Toast.LENGTH_SHORT).show()}
        )

        btnStart.setOnClickListener {
            allMessage?.let { all: AllMessage ->
                val index = Random.nextInt(0, all.messages.size)
                val message: String = all.messages[index]
                (application as AnnoyingExApp).exNotificationManager.sendNewMessage(message)
            }
        }
    }

    private fun fillMessage(all: AllMessage) {
        allMessage = all
    }
}
