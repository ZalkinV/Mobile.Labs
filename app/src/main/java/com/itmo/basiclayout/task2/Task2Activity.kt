package com.itmo.basiclayout.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.itmo.basiclayout.databinding.ActivityTask2Binding
import com.itmo.basiclayout.task2.Consts

class Task2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask2Binding

    private lateinit var threadWorker1 : ThreadWorker
    private lateinit var threadWorker2 : ThreadWorker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeWorkers()
        startWorkers()
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

    private fun setNumber(textView: TextView, number: Int) {
        runOnUiThread {
            textView.text = number.toString()
        }
    }
}
