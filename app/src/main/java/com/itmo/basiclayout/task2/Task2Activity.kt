package com.itmo.basiclayout.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.itmo.basiclayout.R
import com.itmo.basiclayout.databinding.ActivityTask2Binding
import kotlin.concurrent.thread

class Task2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        startThreads()
    }

    private fun startThreads() {
        val thread1 = thread {
            while (true) {
                increaseNumbers(binding.t2TvFirst)
                Thread.sleep(600)
            }
        }

        val thread2 = thread {
            while (true) {
                increaseNumbers(binding.t2TvSecond)
                Thread.sleep(400)
            }
        }
    }

    private fun increaseNumbers(textView: TextView) {
        val newValue = (Integer.parseInt(textView.text.toString()) + 1).toString()

        runOnUiThread {
            textView.text = newValue
        }
    }
}
