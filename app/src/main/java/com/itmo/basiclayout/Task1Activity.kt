package com.itmo.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class Task1Activity : AppCompatActivity() {
    private val switcher: SwitchCompat by lazy { findViewById(R.id.switch1) }
    private val textView: TextView by lazy { findViewById(R.id.textView) }

    private val buttonHideList: Button by lazy { findViewById(R.id.button_hideList) }
    private val listView: ListView by lazy { findViewById(R.id.listview) }

    private val buttonToast: Button by lazy { findViewById(R.id.button_toast) }

    private val fab: FloatingActionButton by lazy { findViewById(R.id.floatingActionButton) }
    private val editText: EditText by lazy { findViewById(R.id.editText) }

    private var listViewItems = listOf<ListItemDetails>()

    companion object {
        private const val logTag = "TASK1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        initializeComponents(savedInstanceState)

        setListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putParcelableArrayList(BundleKeysEnum.Task1.LIST_ELEMENTS.name, ArrayList(listViewItems))
            putInt(BundleKeysEnum.Task1.LIST_VISIBILITY.name, listView.visibility)
            putString(BundleKeysEnum.Task1.TEXTVIEW_TEXT.name, textView.text.toString())
            putString(BundleKeysEnum.Task1.EDITTEXT_TEXT.name, editText.text.toString())
            putBoolean(BundleKeysEnum.Task1.SWITCHER_STATE.name, switcher.isChecked)
        }
    }

    private fun initializeComponents(state: Bundle?) {
        if (state != null) with(state) {
            listViewItems = getParcelableArrayList<ListItemDetails>(BundleKeysEnum.Task1.LIST_ELEMENTS.name)?.toList() ?: emptyList()
            listView.visibility = getInt(BundleKeysEnum.Task1.LIST_VISIBILITY.name)
            textView.text = getString(BundleKeysEnum.Task1.TEXTVIEW_TEXT.name)
            editText.setText(getString(BundleKeysEnum.Task1.EDITTEXT_TEXT.name))
            switcher.isChecked = getBoolean(BundleKeysEnum.Task1.SWITCHER_STATE.name)
        } else {
            fetchData()
        }

        setSwitcherColor(switcher.isChecked)
        populateListView()
    }

    private fun fetchData() {
        listViewItems = InMemoryDataSource().fetchData(10)
    }

    private fun setListeners() {
        switcher.setOnCheckedChangeListener { _, isChecked -> setSwitcherColor(isChecked) }

        buttonHideList.setOnClickListener {
            listView.visibility = when(listView.visibility) {
                View.INVISIBLE -> View.VISIBLE
                View.VISIBLE -> View.INVISIBLE
                else -> View.VISIBLE
            }

            val fromIntent = intent.getStringExtra(IntentKeysEnum.INFO.name)
            Log.d(logTag, "Button to hide list was clicked")
            Log.i(logTag, "Intent from prev activity: ${fromIntent.orEmpty()}")
        }

        buttonToast.setOnClickListener {
            Toast.makeText(this, R.string.toast_text, Toast.LENGTH_SHORT).show()
            Log.d(logTag, "Toast button was clicked")
        }

        fab.setOnClickListener {
            textView.text = editText.text
            Snackbar.make(it, "EditText was changed", Snackbar.LENGTH_SHORT)
                .show()
        }

        listView.setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, DetailsActivity::class.java)
            val details = listViewItems[i]
            intent.putExtra(IntentKeysEnum.Task1.DETAILS.name, details)

            startActivity(intent)
        }
    }

    private fun populateListView() {
        val values = listViewItems.map { it.title }
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, values)
        listView.adapter = adapter
    }

    private fun setSwitcherColor(isChecked: Boolean) {
        val colorResource =
            if (isChecked) R.color.green
            else R.color.red

        textView.setBackgroundResource(colorResource)
    }
}