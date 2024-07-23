package com.example.looperhandlermessagequeue

import android.os.Handler
import android.os.Looper

fun main() {

}

fun sendMessage() {
    val handlerWorkerThread = HandlerWorkerThread("HandlerWorkerThread")
    handlerWorkerThread.execute {
        println("Hello from HandlerWorkerThread")
    }
}

fun methodWorkingOnMainTread(callback: () -> Unit) {
    Thread(){
        Thread.sleep(1000)
        Handler(Looper.getMainLooper()).post{
            callback()
        }
    }.start()

}


fun methodWorkingOnMainThread(callback: () -> Unit) {
    val firstHandler = Handler()
    Thread {
        // Simulate doing some work in the background thread
        Thread.sleep(1000) // Example: Simulating work by sleeping for 1 second

        // Work is done, now post the callback to be executed on the main thread
   //   val secondHandler=  Handler()//Handler(Looper.getMainLooper()) is wright variant because in backgroundThread we need Looper!!
          firstHandler.post {
            callback()
        }
//        secondHandler.post {
//            callback()
//        }
    }.start()
}