package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.itmo.basiclayout.sequences.CollatzSequence
import com.itmo.basiclayout.sequences.FibonacciSequence
import com.itmo.basiclayout.sequences.NaturalSequence

class DetailsActivity : AppCompatActivity() {

    private lateinit var text_imageLabel: TextView
    private lateinit var text_description: TextView
    private lateinit var image_icon: ImageView

    private lateinit var button_nat: Button
    private lateinit var text_nat: TextView
    private var sequenceNat = NaturalSequence(1L)

    private lateinit var button_fib: Button
    private lateinit var text_fib: TextView
    private var sequenceFib = FibonacciSequence(0L, 1L)

    private lateinit var button_col: Button
    private lateinit var text_col: TextView
    private var sequenceCol = CollatzSequence(37L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeComponents()

        val listItemDetails = intent.getSerializableExtra(IntentKeysEnum.Task1.DETAILS.name)
        if (listItemDetails is ListItemDetails) {
            applyListItemDetails(listItemDetails)
        }

        setListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putLong(BundleKeysEnum.Task1.NUM_NAT.name, sequenceNat.value)
        outState.putLong(BundleKeysEnum.Task1.NUM_FIB_PREV.name, sequenceFib.prevValue)
        outState.putLong(BundleKeysEnum.Task1.NUM_FIB.name, sequenceFib.value)
        outState.putLong(BundleKeysEnum.Task1.NUM_COL.name, sequenceCol.value)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        sequenceNat = NaturalSequence(savedInstanceState.getLong(BundleKeysEnum.Task1.NUM_NAT.name))
        sequenceFib = FibonacciSequence(
            savedInstanceState.getLong(BundleKeysEnum.Task1.NUM_FIB_PREV.name),
            savedInstanceState.getLong(BundleKeysEnum.Task1.NUM_FIB.name))
        sequenceCol = CollatzSequence(savedInstanceState.getLong(BundleKeysEnum.Task1.NUM_COL.name))
        displaySequenceNumbers()
    }

    private fun initializeComponents() {
        text_imageLabel = findViewById(R.id.text_imageLabel)
        text_description = findViewById(R.id.text_description)
        image_icon = findViewById(R.id.image_icon)

        button_nat = findViewById(R.id.button_nat)
        text_nat = findViewById(R.id.text_nat)

        button_fib = findViewById(R.id.button_fib)
        text_fib = findViewById(R.id.text_fib)

        button_col = findViewById(R.id.button_col)
        text_col = findViewById(R.id.text_col)

        displaySequenceNumbers()
    }

    private fun displaySequenceNumbers() {
        text_nat.text = sequenceNat.value.toString()
        text_fib.text = sequenceFib.prevValue.toString()
        text_col.text = sequenceCol.value.toString()
    }

    private fun applyListItemDetails(details: ListItemDetails) {
        text_imageLabel.text = details.title
        text_description.text = details.description
        val iconId = when(details.icon) {
            IconEnum.SCANNER -> R.drawable.ic_outline_adf_scanner_24
            IconEnum.CLOUD -> R.drawable.ic_outline_cloud_24
            IconEnum.CORONAVIRUS -> R.drawable.ic_outline_coronavirus_24
            IconEnum.DESCRIPTION -> R.drawable.ic_outline_description_24
        }
        image_icon.setImageResource(iconId)
    }

    private fun setListeners() {
        button_nat.setOnClickListener {
            text_nat.text = sequenceNat.getNext().toString()
        }

        button_fib.setOnClickListener {
            text_fib.text = sequenceFib.getNext().toString()
        }

        button_col.setOnClickListener {
            text_col.text = sequenceCol.getNext().toString()
        }
    }
}