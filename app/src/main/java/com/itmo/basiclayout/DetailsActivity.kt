package com.itmo.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    private lateinit var imageLabel: TextView
    private lateinit var description: TextView
    private lateinit var imageViewIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val listItemDetails = intent.getSerializableExtra(IntentKeys.Task1.DETAILS.name)
        if (listItemDetails is ListItemDetails) {
            initializeComponents(listItemDetails)
        }
    }

    private fun initializeComponents(details: ListItemDetails) {
        imageLabel = findViewById(R.id.imageLabel)
        imageLabel.text = details.title

        description = findViewById(R.id.description)
        description.text = details.description

        imageViewIcon = findViewById(R.id.imageViewIcon)
        val iconId = when(details.icon) {
            IconEnum.SCANNER -> R.drawable.ic_outline_adf_scanner_24
            IconEnum.CLOUD -> R.drawable.ic_outline_cloud_24
            IconEnum.CORONAVIRUS -> R.drawable.ic_outline_coronavirus_24
            IconEnum.DESCRIPTION -> R.drawable.ic_outline_description_24
        }
        imageViewIcon.setImageResource(iconId)
    }
}