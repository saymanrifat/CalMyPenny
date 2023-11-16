package com.ounicsoft.calmypenny.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.FragmentWalletBinding
import com.ounicsoft.calmypenny.viewmodel.WalletViewModel
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.ounicsoft.calmypenny.databinding.DialogAddNewWalletBinding
import com.ounicsoft.calmypenny.ui.adapter.WalletListAdapter
import com.ounicsoft.calmypenny.ui.utils.MyToast


class WalletFragment : Fragment(), WalletListAdapter.WalletClickListener {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var walletAdapter: WalletListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var walletViewModel: WalletViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var edWalletName: EditText
    private lateinit var edInitialBalance: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(layoutInflater, container, false)

        // RecyclerViewSetup
        recyclerViewSetup()


        //Default XML
        return binding.root
    }

    private fun recyclerViewSetup() {
        recyclerView = binding.recyclerViewWalletList
        walletAdapter = WalletListAdapter(requireActivity(), ArrayList<WalletModel>(), this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = walletAdapter
        }
        walletViewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        walletViewModel.getAllWallet(requireActivity()).observe(requireActivity(), Observer {
            walletAdapter.setData(it as ArrayList<WalletModel>)
        })
        binding.btnAddWalletActivity.setOnClickListener {
            openAddWalletDialog()
        }
    }


    override fun onClick(walletEntry: WalletModel) {
        openDialogToUpdate(walletEntry)
    }

    override fun onLongClick(walletEntry: WalletModel) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Are you sure?").setTitle("Delete Wallet")
            .setPositiveButton("No") { dialog, which ->
            }.setNegativeButton("Yes") { dialog, which ->
                walletViewModel.delete(
                    requireActivity(), walletEntry
                )
                MyToast(requireContext(), "Deleted").showToast()
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun openAddWalletDialog() {
        builder = AlertDialog.Builder(requireActivity())
        val dialogAddNewWalletBinding = DialogAddNewWalletBinding.inflate(layoutInflater)
        dialog = builder.create()
        dialog.setView(dialogAddNewWalletBinding.root)
        edWalletName = dialogAddNewWalletBinding.edWalletName
        edInitialBalance = dialogAddNewWalletBinding.edInitialBalance
        dialogAddNewWalletBinding.btnSave.setOnClickListener {
            insertDataIntoDatabase()
        }
        dialog.show()
    }


    private fun openDialogToUpdate(walletEntry: WalletModel) {
        builder = AlertDialog.Builder(requireActivity())
        val dialogAddNewWalletBinding = DialogAddNewWalletBinding.inflate(layoutInflater)
        dialog = builder.create()
        dialog.setView(dialogAddNewWalletBinding.root)
        edWalletName = dialogAddNewWalletBinding.edWalletName
        edInitialBalance = dialogAddNewWalletBinding.edInitialBalance

        edWalletName.setText(walletEntry.walletName)
        edInitialBalance.setText(walletEntry.totalAmount.toString())

        dialogAddNewWalletBinding.btnSave.setOnClickListener {
            updateDataIntoDatabase(walletEntry)
        }
        dialog.show()
    }


    private fun insertDataIntoDatabase() {
        val walletName = edWalletName.text
        val initialBalance = edInitialBalance.text
        if (!TextUtils.isEmpty(walletName) && !TextUtils.isEmpty(initialBalance)) {
            val walletNameStr = walletName.toString().trim()
            val initialBalanceStr = initialBalance.toString().toDouble()
            walletViewModel.insert(
                requireActivity(), WalletModel(null, walletNameStr, initialBalanceStr, Color.GRAY)
            )

            MyToast(requireActivity(), "Wallet Added").showToast()
            Toast.makeText(context, "Data added successfully..", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        } else {
            if (TextUtils.isEmpty(walletName)) {
                edWalletName.error = "This field is required"
            }
            if (TextUtils.isEmpty(initialBalance.toString())) {
                edInitialBalance.error = "This field is required"
            }
        }
    }

    private fun updateDataIntoDatabase(walletEntry: WalletModel) {
        val walletName = edWalletName.text.toString().trim()
        val initialBalance = edInitialBalance.text.toString().toDouble()
        if (!TextUtils.isEmpty(walletName) && !TextUtils.isEmpty(initialBalance.toString())) {


            walletViewModel.update(
                requireActivity(),
                WalletModel(walletEntry.walletId, walletName, initialBalance, Color.GRAY)
            )
            MyToast(requireActivity(), "Updated").showToast()
            dialog.dismiss()
        } else {
            MyToast(requireContext(), "Please fill all the fields..").showToast()
        }
    }
}