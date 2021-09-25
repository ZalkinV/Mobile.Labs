package com.itmo.basiclayout

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val switcher: Switch by lazy { findViewById(R.id.switch1) }
    val textView: TextView by lazy { findViewById(R.id.textView) }

    val buttonHideList: Button by lazy { findViewById(R.id.button_hidelist) }
    val listView: ListView by lazy { findViewById(R.id.listview) }

    companion object {
        private const val logInfoTag = "INFO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switcher.setOnCheckedChangeListener { _, isChecked ->
            val colorResource =
                if (isChecked) R.color.label_color_switch_true
                else R.color.label_color_switch_false

            textView.setBackgroundResource(colorResource)
        }

        buttonHideList.setOnClickListener {
            listView.visibility = when(listView.visibility) {
                View.GONE -> View.VISIBLE
                View.VISIBLE -> View.GONE
                else -> View.GONE
            }

            Log.d(logInfoTag, "Button to hide list was clicked")
        }
    }
}