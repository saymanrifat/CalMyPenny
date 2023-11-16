package com.ounicsoft.calmypenny.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import android.widget.ToggleButton
import com.ounicsoft.calmypenny.R
import com.ounicsoft.calmypenny.databinding.ActivityAddEntryBinding
import com.ounicsoft.calmypenny.ui.utils.MyToast

class AddEntryActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var walletSpinner: Spinner
    private lateinit var spSelectWalletTo: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var tBtnPlusAmount: RadioButton
    private lateinit var tBtnMinusAmount: RadioButton
    private lateinit var tBtnTransferAmount: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialization Spinner
        walletSpinner = binding.spSelectWalletFrom
        spSelectWalletTo = binding.spSelectWalletTo
        //Initialization Radio Button
        setupRadioButton()
        binding.radioGroup.check(binding.tBtnPlusAmount.id)
        //Spinner Setup
        setupSpinnerForFromAccount()
        setupSpinnerForToAccount()


    }

    private fun setupSpinnerForFromAccount() {
        walletSpinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.sample_wallet_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            walletSpinner.adapter = adapter
        }
    }

    private fun setupSpinnerForToAccount() {
        spSelectWalletTo.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.sample_wallet_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spSelectWalletTo.adapter = adapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selected: Any? = parent?.getItemAtPosition(position)
        Toast.makeText(this, "$selected", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun setupRadioButton() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.tBtnPlusAmount.id -> {
                    binding.transferSelector.visibility = View.INVISIBLE
                    MyToast(this, "tBtnPlusAmount").showToast()
                }

                binding.tBtnMinusAmount.id -> {
                    binding.transferSelector.visibility = View.INVISIBLE
                    MyToast(this, "tBtnMinusAmount").showToast()
                }

                binding.tBtnTransferAmount.id -> {
                    binding.transferSelector.visibility = View.VISIBLE
                    MyToast(this, "tBtnTransferAmount").showToast()
                }
            }
        }
    }
}