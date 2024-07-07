package com.example.looperhandlermessagequeue

import android.os.Handler
import android.os.Looper

class LooperWorkerThread : Thread() {

    private lateinit var handler: Handler

    init {
        start()
    }

    override fun run() {

        // Prepare a looper on the current thread.
        // The current thread is being detected implicitly
        Looper.prepare()

        // Attach the Handler to the Looper of current thread
        handler = Handler(Looper.myLooper()!!)

        // After the following line this thread will start
        // running the message loop and will not normally
        // exit the loop unless a problem happens or you
        // call quit()
        Looper.loop()

    }

    fun execute(block: () -> Unit) {
        handler.post { block() }
    }

    fun quit() {
        Looper.myLooper()?.quit()
    }


}



