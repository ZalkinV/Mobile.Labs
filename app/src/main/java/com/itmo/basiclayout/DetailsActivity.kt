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
    private val sequenceNatStart = 1L

    private lateinit var button_fib: Button
    private lateinit var text_fib: TextView
    private val sequenceFibStart = 0L

    private lateinit var button_col: Button
    private lateinit var text_col: TextView
    private val sequenceColStart = 37L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeComponents()

        val listItemDetails = intent.getSerializableExtra(IntentKeys.Task1.DETAILS.name)
        if (listItemDetails is ListItemDetails) {
            applyListItemDetails(listItemDetails)
        }

        setListeners()
    }

    private fun initializeComponents() {
        text_imageLabel = findViewById(R.id.text_imageLabel)
        text_description = findViewById(R.id.text_description)
        image_icon = findViewById(R.id.image_icon)

        button_nat = findViewById(R.id.button_nat)
        text_nat = findViewById(R.id.text_nat)
        text_nat.text = sequenceNatStart.toString()

        button_fib = findViewById(R.id.button_fib)
        text_fib = findViewById(R.id.text_fib)
        text_fib.text = sequenceFibStart.toString()

        button_col = findViewById(R.id.button_col)
        text_col = findViewById(R.id.text_col)
        text_col.text = sequenceColStart.toString()
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
        val sequenceNat = NaturalSequence(sequenceNatStart)
        button_nat.setOnClickListener {
            text_nat.text = sequenceNat.getNext().toString()
        }

        val sequenceFib = FibonacciSequence()
        button_fib.setOnClickListener {
            text_fib.text = sequenceFib.getNext().toString()
        }

        val sequenceCol = CollatzSequence(sequenceColStart)
        button_col.setOnClickListener {
            text_col.text = sequenceCol.getNext().toString()
        }
    }
}