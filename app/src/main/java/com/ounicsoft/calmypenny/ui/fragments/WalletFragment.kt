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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            openDialog()
        }
    }

    private fun openDialog() {
        builder = AlertDialog.Builder(requireActivity())
        val dialogAddNewWalletBinding = DialogAddNewWalletBinding.inflate(layoutInflater)
        dialog = builder.create()
        dialog.setView(dialogAddNewWalletBinding.root)
        edWalletName = dialogAddNewWalletBinding.edWalletName
        edInitialBalance = dialogAddNewWalletBinding.edInitialBalance
        dialogAddNewWalletBinding.btnSave.setOnClickListener {
            saveDataIntoDatabase()
        }
        dialog.show()
    }

    private fun saveDataIntoDatabase() {
        val walletName = edWalletName.text.toString().trim()
        val initialBalance = edInitialBalance.text.toString().toDouble()
        if (!TextUtils.isEmpty(walletName) && !TextUtils.isEmpty(initialBalance.toString())) {
            walletViewModel.insert(
                requireActivity(),
                WalletModel(null, walletName, initialBalance, Color.GRAY)
            )
            Toast.makeText(context, "Data added successfully..", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        } else {
            Toast.makeText(context, "Please fill all the fields..", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onClick(walletEntry: WalletModel) {
        Toast.makeText(context, "OnClick", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(walletEntry: WalletModel) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder
            .setMessage("Are you sure?")
            .setTitle("Warning")
            .setPositiveButton("No") { dialog, which ->
            }
            .setNegativeButton("Yes") { dialog, which ->
                walletViewModel.delete(
                    requireActivity(),
                    walletEntry
                )
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}