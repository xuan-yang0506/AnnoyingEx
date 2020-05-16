package xuany2.washington.annoyingex.manager

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import xuany2.washington.annoyingex.model.AllMessage

class ApiManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchSentences(onRequestReady: (AllMessage) -> Unit, onFetchError: (() -> Unit)?) {
        val url = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                val gson = Gson()
                val allMessage = gson.fromJson(response, AllMessage::class.java)
                onRequestReady(allMessage)
            },
            {
                onFetchError?.invoke()
            }
        )
        queue.add(request)
    }

}