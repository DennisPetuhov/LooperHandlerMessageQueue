package com.example.looperhandlermessagequeue

import android.os.Handler
import android.os.HandlerThread

class HandlerWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var handler: Handler

    init {
        start()
    }

    override fun onLooperPrepared() {
        handler = Handler()
    }

    fun execute(block: () -> Unit) {
        if (::handler.isInitialized) {
            handler.post { block() }
        }
    }

}