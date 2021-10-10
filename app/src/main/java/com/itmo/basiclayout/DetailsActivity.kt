package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    private lateinit var imageLabel: TextView
    private lateinit var description: TextView
    private lateinit var imageViewIcon: ImageView

    private lateinit var buttonNat: Button
    private lateinit var textViewNat: TextView

    private lateinit var buttonFib: Button
    private lateinit var textViewFib: TextView

    private lateinit var buttonCol: Button
    private lateinit var textViewCol: TextView

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

        buttonFib = findViewById(R.id.buttonFib)
        textViewFib = findViewById(R.id.textViewFib)

        buttonCol = findViewById(R.id.buttonCol)
        textViewCol = findViewById(R.id.textViewCol)
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
        var sequenceNat = 1;
        buttonNat.setOnClickListener {
            sequenceNat += 1
            textViewNat.text = sequenceNat.toString()
        }

        var sequenceFib = 2;
        buttonFib.setOnClickListener {
            sequenceFib += 2
            textViewFib.text = sequenceFib.toString()
        }

        var sequenceCol = 1;
        buttonCol.setOnClickListener {
            sequenceCol += 3
            textViewCol.text = sequenceCol.toString()
        }
    }
}