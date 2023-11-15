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
import com.ounicsoft.calmypenny.data.db.WalletDatabase
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.FragmentWalletBinding
import com.ounicsoft.calmypenny.ui.adapter.WalletHomeScreenAdapter
import com.ounicsoft.calmypenny.viewmodel.WalletViewModel

import android.text.TextUtils

import android.widget.*
import androidx.appcompat.app.AlertDialog

import androidx.lifecycle.Observer
import com.ounicsoft.calmypenny.databinding.DialogBinding


class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var walletAdapter: WalletHomeScreenAdapter
    private lateinit var walletDb: WalletDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var walletViewModel: WalletViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var save: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWalletBinding.inflate(layoutInflater, container, false)




        recyclerView = binding.recyclerViewWalletList
        walletAdapter = WalletHomeScreenAdapter(requireActivity(), ArrayList<WalletModel>())
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



        return binding.root
    }

    private fun openDialog() {
        builder = AlertDialog.Builder(requireActivity())
        var itemView = DialogBinding.inflate(layoutInflater)
        dialog = builder.create()
        dialog.setView(itemView.root)
        name = itemView.name1
        age = itemView.age1
        save = itemView.save
        save.setOnClickListener {
            saveDataIntoDatabase()
        }
        dialog.show()
    }

    private fun saveDataIntoDatabase() {
        val getName = name.text.toString().trim()
        val getAge = age.text.toString().toDouble()
        if (!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge.toString())) {
            walletViewModel.insert(
                requireActivity(),
                WalletModel(null, getName, getAge, Color.GRAY)
            )
            Toast.makeText(context, "Data added successfully..", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        } else {
            Toast.makeText(context, "Please fill all the fields..", Toast.LENGTH_SHORT)
                .show()
        }
    }
}