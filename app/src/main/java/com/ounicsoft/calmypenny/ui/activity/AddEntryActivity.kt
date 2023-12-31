package com.ounicsoft.calmypenny.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.databinding.ActivityAddEntryBinding
import com.ounicsoft.calmypenny.ui.utils.MyToast
import com.ounicsoft.calmypenny.viewmodel.TransactionViewModel
import com.ounicsoft.calmypenny.viewmodel.WalletViewModel
import java.text.SimpleDateFormat
import java.util.Date


class AddEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var spSelectWalletFrom: Spinner
    private lateinit var spSelectWalletTo: Spinner
    private lateinit var walletViewModel: WalletViewModel
    private lateinit var edAmountText: EditText
    private lateinit var edExpenseCategoryText: EditText
    private var walletNameArray: ArrayList<String> = ArrayList()
    private lateinit var transactionViewModel: TransactionViewModel
    private var transactionType: Int = 1
    private var spinnerSelectedFromAccountStr: String = ""
    private var spinnerSelectedToAccountStr: String = ""
    private lateinit var currentDateAndTime: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialization Spinner
        spSelectWalletFrom = binding.spSelectWalletFrom
        spSelectWalletTo = binding.spSelectWalletTo
        //Initialization Edittext
        edAmountText = binding.edAmountText
        edExpenseCategoryText = binding.edExpenseCategoryText
        //Initialization Radio Button
        setupRadioButton()
        //Initialization Time Button
        currentDateAndTime = SimpleDateFormat("hh:mm dd/M/yy")
        binding.radioGroup.check(binding.tBtnMinusAmount.id)
        //dataSetOfWalletName
        dataSetOfWalletName()

        binding.saveEntry.setOnClickListener {
            insertDataIntoDatabase()
        }
    }

    private fun insertDataIntoDatabase() {
        val amount = edAmountText.text
        val cause = edExpenseCategoryText.text

        if (!TextUtils.isEmpty(amount) && !TextUtils.isEmpty(cause)) {
            val amountDbl = amount.toString().toDouble()
            val causeStr = cause.toString().trim()
            transactionViewModel =
                ViewModelProvider(this).get(TransactionViewModel::class.java)
            transactionViewModel.insert(
                this,
                TransactionModel(
                    null,
                    amountDbl,
                    transactionType,
                    currentDateAndTime.format(Date()).toString(),
                    causeStr,
                    spinnerSelectedFromAccountStr,
                    if (transactionType == 0) spinnerSelectedToAccountStr else null
                ),
            )
            MyToast(this, "Transaction Added").showToast()
            onBackPressed()
        } else {
            if (TextUtils.isEmpty(amount)) {
                edAmountText.error = "This field is required"
            }
            if (TextUtils.isEmpty(cause)) {
                edExpenseCategoryText.error = "This field is required"
            }
        }
    }

    private fun dataSetOfWalletName() {
        walletViewModel =
            ViewModelProvider(this).get(WalletViewModel::class.java)
        walletViewModel.getAllWalletName(this).observe(this, Observer {
            setData(it as ArrayList<String>)
        })
    }

    private fun setData(userList: ArrayList<String>) {
        walletNameArray = userList
        //Spinner From Setup
        setupSpinnerForFromAccount()
        //Spinner To Setup
        setupSpinnerForToAccount()
    }

    private fun setupSpinnerForFromAccount() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, walletNameArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectWalletFrom.adapter = adapter
        spSelectWalletFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerSelectedFromAccountStr = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupSpinnerForToAccount() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, walletNameArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectWalletTo.adapter = adapter
        spSelectWalletTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerSelectedToAccountStr = parent?.getItemAtPosition(position).toString()
            }
        }
    }

    private fun setupRadioButton() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.tBtnPlusAmount.id -> {
                    binding.transferSelector.visibility = View.INVISIBLE
                    transactionType = 2
                }

                binding.tBtnMinusAmount.id -> {
                    binding.transferSelector.visibility = View.INVISIBLE
                    transactionType = 1
                }

                binding.tBtnTransferAmount.id -> {
                    binding.transferSelector.visibility = View.VISIBLE
                    transactionType = 0
                }
            }
        }
    }
}