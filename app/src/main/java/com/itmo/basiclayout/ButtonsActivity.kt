package com.itmo.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ButtonsActivity : AppCompatActivity() {

    val buttonTask1: Button by lazy { findViewById(R.id.button_task1) }
    val buttonTask2: Button by lazy { findViewById(R.id.button_task2) }
    val buttonTask3: Button by lazy { findViewById(R.id.button_task3) }

    companion object {
        private const val logTag = "BUTTONS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        buttonTask1.setOnClickListener {
            openTask1Activity()
        }

        buttonTask2.setOnClickListener {
            Toast.makeText(this, "Not implemented, yet!", Toast.LENGTH_SHORT).show()
        }

        buttonTask3.setOnClickListener {
            Toast.makeText(this, "Not implemented, yet!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openTask1Activity() {
        val intent = Intent(this, Task1Activity::class.java);
        intent.putExtra(IntentKeys.INFO.name, "Very useful information")

        startActivity(intent)
    }
}