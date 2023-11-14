package com.ounicsoft.calmypenny.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityAddEntryBinding

class AddEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var walletSpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialization Views
        walletSpinner = binding.spSelectWalletFrom
        //Spinner Setup
        setupSpinnerForExpense()

    }

    private fun setupSpinnerForExpense() {
        ArrayAdapter.createFromResource(
            this,
            R.array.sample_wallet_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            walletSpinner.adapter = adapter
        }
    }
}