package com.itmo.basiclayout.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.itmo.basiclayout.databinding.ActivityTask2Binding

class Task2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask2Binding

    private lateinit var threadWorker1 : ThreadWorker
    private lateinit var threadWorker2 : ThreadWorker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeWorkers()
        initializeComponents()
        initializeListeners()
    }

    private fun initializeComponents() {
        displayWorker1Speed()
        displayWorker2Speed()
    }

    private fun initializeListeners() = with(binding) {
        t2BtnRun.setOnClickListener { startWorkers() }
        t2BtnStop.setOnClickListener { stopWorkers() }
        t2BtnReset.setOnClickListener { resetWorkers() }

        t2BtnFirstSpeedup.setOnClickListener {
            threadWorker1.increaseSpeed()
            displayWorker1Speed()
        }
        t2BtnFirstSlowdown.setOnClickListener {
            threadWorker1.decreaseSpeed()
            displayWorker1Speed()
        }
        t2BtnSecondSpeedup.setOnClickListener {
            threadWorker2.increaseSpeed()
            displayWorker2Speed()
        }
        t2BtnSecondSlowdown.setOnClickListener {
            threadWorker2.decreaseSpeed()
            displayWorker2Speed()
        }
    }

    private fun initializeWorkers() {
        threadWorker1 = ThreadWorker(Consts.FIRST_THREAD_BASE_PERIOD_MS) { iteration ->
            setNumber(binding.t2TvFirst, iteration)
        }
        threadWorker2 = ThreadWorker(Consts.SECOND_THREAD_BASE_PERIOD_MS) { iteration ->
            setNumber(binding.t2TvSecond, iteration)
        }
    }

    private fun startWorkers() {
        threadWorker1.start()
        threadWorker2.start()
    }

    private fun stopWorkers() {
        threadWorker1.stop()
        threadWorker2.stop()
    }

    private fun resetWorkers() {
        threadWorker1.reset()
        threadWorker2.reset()
    }

    private fun setNumber(textView: TextView, number: Int) {
        runOnUiThread {
            textView.text = number.toString()
        }
    }

    private fun displayWorker1Speed() {
        binding.t2TvFirstSpeed.text = threadWorker1.workPeriodMs.toString()
    }

    private fun displayWorker2Speed() {
        binding.t2TvSecondSpeed.text = threadWorker2.workPeriodMs.toString()
    }
}
