package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Task1Activity : AppCompatActivity() {
    private val switcher: Switch by lazy { findViewById(R.id.switch1) }
    private val textView: TextView by lazy { findViewById(R.id.textView) }

    private val buttonHideList: Button by lazy { findViewById(R.id.button_hidelist) }
    private val listView: ListView by lazy { findViewById(R.id.listview) }

    private val buttonToast: Button by lazy { findViewById(R.id.button_toast) }

    private val fab: FloatingActionButton by lazy { findViewById(R.id.floatingActionButton) }
    private val editText: EditText by lazy { findViewById(R.id.editText) }

    companion object {
        private const val logTag = "TASK1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        setListeners()
    }

    private fun setListeners() {
        switcher.setOnCheckedChangeListener { _, isChecked ->
            val colorResource =
                if (isChecked) R.color.label_color_switch_true
                else R.color.label_color_switch_false

            textView.setBackgroundResource(colorResource)
        }

        buttonHideList.setOnClickListener {
            listView.visibility = when(listView.visibility) {
                View.INVISIBLE -> View.VISIBLE
                View.VISIBLE -> View.INVISIBLE
                else -> View.VISIBLE
            }

            val fromIntent = intent.getStringExtra(IntentKeys.INFO.name)
            Log.d(logTag, "Button to hide list was clicked")
            Log.i(logTag, "Intent from prev activity: ${fromIntent.orEmpty()}")
        }

        buttonToast.setOnClickListener {
            Toast.makeText(this, R.string.toast_text, Toast.LENGTH_SHORT).show()
            Log.d(logTag, "Toast button was clicked")
        }

        fab.setOnClickListener {
            textView.text = editText.text
        }
    }
}