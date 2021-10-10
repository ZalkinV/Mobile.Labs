package com.itmo.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val listItemDetails = intent.getSerializableExtra(IntentKeys.Task1.DETAILS.name) as ListItemDetails?
    }
}