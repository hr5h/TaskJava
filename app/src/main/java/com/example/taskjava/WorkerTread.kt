package com.example.taskjava

import java.util.concurrent.LinkedBlockingQueue

class WorkerThread : Thread() {
    private val taskQueue = LinkedBlockingQueue<() -> Unit>()
    private var isRunning = true

    override fun run() {
        while (isRunning) {
            try {
                val task = taskQueue.take()
                task()
            } catch (e: InterruptedException) {
                if (!isRunning) break
            }
        }
    }

    fun execute(task: () -> Unit) {
        taskQueue.put(task)
    }

    fun shutdown() {
        isRunning = false
        this.interrupt()
    }
}