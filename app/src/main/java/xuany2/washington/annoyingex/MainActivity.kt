package xuany2.washington.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import xuany2.washington.annoyingex.model.AllMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as AnnoyingExApp).apiManager.fetchSentences(
            {all -> fillMessage(all)}, { Toast.makeText(this, "fetch error", Toast.LENGTH_SHORT).show()}
        )

        btnStart.setOnClickListener {
            (application as AnnoyingExApp).backgroundWorkManager.startSendingMessages()
        }

        btnStop.setOnClickListener {
            (application  as AnnoyingExApp).backgroundWorkManager.stopWork()
        }
    }

    private fun fillMessage(all: AllMessage) {
        (application as AnnoyingExApp).fillMessages(all)
    }
}
