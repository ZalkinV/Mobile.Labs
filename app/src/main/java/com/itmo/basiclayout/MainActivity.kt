package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val switcher: Switch by lazy { findViewById(R.id.switch1) }
    val textView: TextView by lazy { findViewById(R.id.textView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switcher.setOnCheckedChangeListener { _, isChecked ->
            val colorResource =
                if (isChecked) R.color.label_color_switch_true
                else R.color.label_color_switch_false

            textView.setBackgroundResource(colorResource)
        }
    }
}