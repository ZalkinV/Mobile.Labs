package com.itmo.basiclayout.task2

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class ThreadWorker(
    @Volatile var workPeriodMs: Long,
    private val doWork: (iteration: Int) -> Unit
) {

    var isWorking: AtomicBoolean = AtomicBoolean(false)
    var iteration: Int = 0

    private var thread : Thread? = null

    fun start() {
        if (isWorking.get()) return

        isWorking.set(true)

        thread = thread {
            while (isWorking.get()) {
                doWork(iteration)
                iteration++
                Thread.sleep(workPeriodMs)
            }
        }
    }

    fun stop() {
        isWorking.set(false)
    }

    fun reset() {
        stop()
        iteration = 0
        doWork(iteration)
    }

    fun increaseSpeed() {
        workPeriodMs -= Consts.THREAD_DELAY_STEP_MS
        if (workPeriodMs < Consts.THREAD_DELAY_MIN_MS)
            workPeriodMs = Consts.THREAD_DELAY_MIN_MS
    }

    fun decreaseSpeed() {
        workPeriodMs += Consts.THREAD_DELAY_STEP_MS
        if (workPeriodMs > Consts.THREAD_DELAY_MAX_MS)
            workPeriodMs = Consts.THREAD_DELAY_MAX_MS
    }
}
