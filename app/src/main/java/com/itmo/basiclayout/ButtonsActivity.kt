package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class ButtonsActivity : AppCompatActivity() {

    val buttonTask1: Button by lazy { findViewById(R.id.button_task1) }
    val buttonTask2: Button by lazy { findViewById(R.id.button_task2) }
    val buttonTask3: Button by lazy { findViewById(R.id.button_task3) }

    companion object {
        private const val logInfoTag = "INFO_BUTTONS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        buttonTask1.setOnClickListener {
            Toast.makeText(this, "Not implemented, yet!", Toast.LENGTH_SHORT).show()
        }

        buttonTask2.setOnClickListener {
            Log.d(logInfoTag, "Button 2: Not implemented, yet!")
        }
    }
}