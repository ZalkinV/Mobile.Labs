package com.itmo.basiclayout.task2

import kotlin.concurrent.thread

class ThreadWorker(var workPeriodMs: Long, private val doWork: (iteration: Int) -> Unit) {

    var isWorking: Boolean = false
    var iteration: Int = 0

    private var thread : Thread? = null

    fun start() {
        if (isWorking) return

        isWorking = true

        thread = thread {
            while (isWorking) {
                doWork(iteration)
                iteration++
                Thread.sleep(workPeriodMs)
            }
        }
    }

    fun stop() {
        isWorking = false
    }

    fun reset() {
        iteration = 0
        doWork(iteration)
        stop()
    }
}
