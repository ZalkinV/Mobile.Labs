package com.itmo.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.itmo.basiclayout.dataSources.InMemoryDataSource
import com.itmo.basiclayout.databinding.ActivityTask1Binding
import com.itmo.basiclayout.keysEnums.BundleKeysEnum
import com.itmo.basiclayout.keysEnums.IntentKeysEnum

class Task1Activity : AppCompatActivity() {

    companion object {
        private const val logTag = "TASK1"
    }

    private lateinit var binding: ActivityTask1Binding

    private var listViewItems = listOf<ListItemDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeComponents(savedInstanceState)

        setListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        binding.apply {
            outState.apply {
                putParcelableArrayList(BundleKeysEnum.Task1.LIST_ELEMENTS.name, ArrayList(listViewItems))
                putInt(BundleKeysEnum.Task1.LIST_VISIBILITY.name, listView.visibility)
                putString(BundleKeysEnum.Task1.TEXTVIEW_TEXT.name, textView.text.toString())
                putString(BundleKeysEnum.Task1.EDITTEXT_TEXT.name, editText.text.toString())
                putBoolean(BundleKeysEnum.Task1.SWITCHER_STATE.name, switcher.isChecked)
            }
        }
    }

    private fun initializeComponents(state: Bundle?) {
        if (state != null) with(state) {
            binding.apply {
                listViewItems = getParcelableArrayList<ListItemDetails>(BundleKeysEnum.Task1.LIST_ELEMENTS.name)?.toList() ?: emptyList()
                listView.visibility = getInt(BundleKeysEnum.Task1.LIST_VISIBILITY.name)
                textView.text = getString(BundleKeysEnum.Task1.TEXTVIEW_TEXT.name)
                editText.setText(getString(BundleKeysEnum.Task1.EDITTEXT_TEXT.name))
                switcher.isChecked = getBoolean(BundleKeysEnum.Task1.SWITCHER_STATE.name)
            }
        } else {
            listViewItems = InMemoryDataSource().fetchData(10)
        }

        setTextViewColor(binding.switcher.isChecked)
        populateListView()
    }

    private fun setListeners() = binding.apply {
        switcher.setOnCheckedChangeListener { _, isChecked -> setTextViewColor(isChecked) }

        buttonHideList.setOnClickListener {
            listView.visibility = when(listView.visibility) {
                View.INVISIBLE -> View.VISIBLE
                View.VISIBLE -> View.INVISIBLE
                else -> View.VISIBLE
            }

            Log.d(logTag, "Button to hide list was clicked")
        }

        buttonToast.setOnClickListener {
            Toast.makeText(baseContext, R.string.toast_text, Toast.LENGTH_SHORT).show()
            Log.d(logTag, "Toast button was clicked")
        }

        floatingActionButton.setOnClickListener {
            textView.text = editText.text
            Snackbar.make(it, "EditText was changed", Snackbar.LENGTH_SHORT).show()
        }

        listView.setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(baseContext, DetailsActivity::class.java).apply {
                putExtra(IntentKeysEnum.Task1.DETAILS.name, listViewItems[i])
            }

            startActivity(intent)
        }
    }

    private fun populateListView() = binding.listView.apply {
        adapter = ArrayAdapter(
            baseContext,
            R.layout.support_simple_spinner_dropdown_item,
            listViewItems.map { it.title })
    }

    private fun setTextViewColor(isSwitcherChecked: Boolean) = binding.textView.apply {
        setBackgroundResource(
            if (isSwitcherChecked) R.color.green else R.color.red)
    }
}
