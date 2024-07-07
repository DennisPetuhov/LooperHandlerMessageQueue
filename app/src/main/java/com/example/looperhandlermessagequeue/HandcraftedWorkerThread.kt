package com.example.looperhandlermessagequeue

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class HandcraftedWorkerThread : Thread() {

    private val isAlive = AtomicBoolean(true)
    private val taskQueue = ConcurrentLinkedQueue<Runnable>()

    init {
        start()
    }

    override fun run() {
        while (isAlive.get()) {
            if (taskQueue.isNotEmpty()) {
                val task: Runnable = taskQueue.poll()
                task.run()
            }
        }
    }

    fun execute(block: () -> Unit) {
        taskQueue.add(Runnable { block() })
    }

    fun quit() {
        isAlive.set(false)
    }

}