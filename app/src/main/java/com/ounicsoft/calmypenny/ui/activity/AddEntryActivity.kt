package com.ounicsoft.calmypenny.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ounicsoft.calmypenny.databinding.ActivityAddEntryBinding
import com.ounicsoft.calmypenny.ui.utils.MyToast
import com.ounicsoft.calmypenny.viewmodel.WalletViewModel


class AddEntryActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var spSelectWalletFrom: Spinner
    private lateinit var spSelectWalletTo: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var tBtnPlusAmount: RadioButton
    private lateinit var tBtnMinusAmount: RadioButton
    private lateinit var tBtnTransferAmount: RadioButton
    private lateinit var walletViewModel: WalletViewModel
    private var walletNameArray: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialization Spinner
        spSelectWalletFrom = binding.spSelectWalletFrom
        spSelectWalletTo = binding.spSelectWalletTo
        //Initialization Radio Button
        setupRadioButton()
        binding.radioGroup.check(binding.tBtnPlusAmount.id)
        //dataSetOfWalletName
        dataSetOfWalletName()

    }

    private fun dataSetOfWalletName() {
        walletViewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        walletViewModel.getAllWalletName(this).observe(this, Observer {
            setData(it as ArrayList<String>)
        })
    }

    private fun setData(userList: ArrayList<String>) {
        walletNameArray = userList
        MyToast(this, walletNameArray[0] + "All Ok").showToast()
        //Spinner From Setup
        setupSpinnerForFromAccount()
        //Spinner To Setup
        setupSpinnerForToAccount()
    }

    private fun setupSpinnerForFromAccount() {
        //spSelectWalletTo.onItemSelectedListener = this
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, walletNameArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectWalletFrom.adapter = adapter
    }

    private fun setupSpinnerForToAccount() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, walletNameArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectWalletTo.adapter = adapter
        //spSelectWalletTo.onItemSelectedListener = this
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
                }

                binding.tBtnMinusAmount.id -> {
                    binding.transferSelector.visibility = View.INVISIBLE
                }

                binding.tBtnTransferAmount.id -> {
                    binding.transferSelector.visibility = View.VISIBLE
                }
            }
        }
    }
}