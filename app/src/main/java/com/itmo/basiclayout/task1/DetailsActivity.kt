package com.itmo.basiclayout.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itmo.basiclayout.R
import com.itmo.basiclayout.databinding.ActivityDetailsBinding
import com.itmo.basiclayout.task1.keysEnums.BundleKeysEnum
import com.itmo.basiclayout.task1.keysEnums.IconEnum
import com.itmo.basiclayout.task1.keysEnums.IntentKeysEnum
import com.itmo.basiclayout.task1.sequences.CollatzSequence
import com.itmo.basiclayout.task1.sequences.FibonacciSequence
import com.itmo.basiclayout.task1.sequences.NaturalSequence

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var sequenceNat = NaturalSequence(1L)
    private var sequenceFib = FibonacciSequence(0L, 1L)
    private var sequenceCol = CollatzSequence(37L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeSequences(savedInstanceState)
        initializeComponents()

        setListeners()
    }

    private fun initializeSequences(state: Bundle?) {
        state?.let {
            sequenceNat = NaturalSequence(
                it.getLong(BundleKeysEnum.Details.NUM_NAT.name))
            sequenceFib = FibonacciSequence(
                it.getLong(BundleKeysEnum.Details.NUM_FIB_PREV.name),
                it.getLong(BundleKeysEnum.Details.NUM_FIB.name))
            sequenceCol = CollatzSequence(
                it.getLong(BundleKeysEnum.Details.NUM_COL.name))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putLong(BundleKeysEnum.Details.NUM_NAT.name, sequenceNat.value)
            putLong(BundleKeysEnum.Details.NUM_FIB_PREV.name, sequenceFib.prevValue)
            putLong(BundleKeysEnum.Details.NUM_FIB.name, sequenceFib.value)
            putLong(BundleKeysEnum.Details.NUM_COL.name, sequenceCol.value)
        }
    }

    private fun initializeComponents() = binding.apply {
        textNat.text = sequenceNat.value.toString()
        textFib.text = sequenceFib.prevValue.toString()
        textCol.text = sequenceCol.value.toString()

        val listItemDetails = intent.getParcelableExtra<ListItemDetails>(IntentKeysEnum.DETAILS.name)
        listItemDetails?.let {
            textImageLabel.text = it.title
            textDescription.text = it.description
            val iconId = when(it.icon) {
                IconEnum.SCANNER -> R.drawable.ic_outline_adf_scanner_24
                IconEnum.CLOUD -> R.drawable.ic_outline_cloud_24
                IconEnum.CORONAVIRUS -> R.drawable.ic_outline_coronavirus_24
                IconEnum.DESCRIPTION -> R.drawable.ic_outline_description_24
            }
            imageIcon.setImageResource(iconId)
        }
    }

    private fun setListeners() = binding.apply {
        buttonNat.setOnClickListener {
            textNat.text = sequenceNat.getNext().toString()
        }

        buttonFib.setOnClickListener {
            textFib.text = sequenceFib.getNext().toString()
        }

        buttonCol.setOnClickListener {
            textCol.text = sequenceCol.getNext().toString()
        }
    }
}
