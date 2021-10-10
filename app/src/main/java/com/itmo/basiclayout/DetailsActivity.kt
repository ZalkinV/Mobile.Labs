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

    private lateinit var imageLabel: TextView
    private lateinit var description: TextView
    private lateinit var imageViewIcon: ImageView

    private lateinit var buttonNat: Button
    private lateinit var textViewNat: TextView
    private val sequenceNatStart = 1L

    private lateinit var buttonFib: Button
    private lateinit var textViewFib: TextView
    private val sequenceFibStart = 0L

    private lateinit var buttonCol: Button
    private lateinit var textViewCol: TextView
    private val sequenceColStart = 1L

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
        imageLabel = findViewById(R.id.imageLabel)
        description = findViewById(R.id.description)
        imageViewIcon = findViewById(R.id.imageViewIcon)

        buttonNat = findViewById(R.id.buttonNat)
        textViewNat = findViewById(R.id.textViewNat)
        textViewNat.text = sequenceNatStart.toString()

        buttonFib = findViewById(R.id.buttonFib)
        textViewFib = findViewById(R.id.textViewFib)
        textViewFib.text = sequenceFibStart.toString()

        buttonCol = findViewById(R.id.buttonCol)
        textViewCol = findViewById(R.id.textViewCol)
        textViewCol.text = sequenceColStart.toString()
    }

    private fun applyListItemDetails(details: ListItemDetails) {
        imageLabel.text = details.title
        description.text = details.description
        val iconId = when(details.icon) {
            IconEnum.SCANNER -> R.drawable.ic_outline_adf_scanner_24
            IconEnum.CLOUD -> R.drawable.ic_outline_cloud_24
            IconEnum.CORONAVIRUS -> R.drawable.ic_outline_coronavirus_24
            IconEnum.DESCRIPTION -> R.drawable.ic_outline_description_24
        }
        imageViewIcon.setImageResource(iconId)
    }

    private fun setListeners() {
        val sequenceNat = NaturalSequence(sequenceNatStart)
        buttonNat.setOnClickListener {
            textViewNat.text = sequenceNat.getNext().toString()
        }

        val sequenceFib = FibonacciSequence()
        buttonFib.setOnClickListener {
            textViewFib.text = sequenceFib.getNext().toString()
        }

        val sequenceCol = CollatzSequence(sequenceColStart)
        buttonCol.setOnClickListener {
            textViewCol.text = sequenceCol.getNext().toString()
        }
    }
}