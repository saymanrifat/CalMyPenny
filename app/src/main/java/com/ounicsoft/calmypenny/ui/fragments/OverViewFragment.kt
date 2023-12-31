package com.ounicsoft.calmypenny.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.FragmentOverViewBinding
import com.ounicsoft.calmypenny.ui.activity.AddEntryActivity
import com.ounicsoft.calmypenny.ui.adapter.TransactionAdapter
import com.ounicsoft.calmypenny.ui.adapter.WalletHomeScreenAdapter
import com.ounicsoft.calmypenny.viewmodel.TransactionViewModel
import com.ounicsoft.calmypenny.viewmodel.WalletViewModel
import ir.mahozad.android.PieChart

class OverViewFragment : Fragment() {
    private lateinit var binding: FragmentOverViewBinding
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var walletAdapter: WalletHomeScreenAdapter
    private lateinit var recyclerViewTransactionList: RecyclerView
    private lateinit var recyclerViewWalletList: RecyclerView
    private lateinit var walletViewModel: WalletViewModel
    private lateinit var transactionViewModel: TransactionViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverViewBinding.inflate(layoutInflater, container, false)
        //PieChart Setup
        pieChart()
        //Transaction RecyclerView Setup
        recyclerViewSetupTransaction()
        //Wallet RecyclerView Setup
        recyclerViewSetupWallet()
        goToAddEntryActivity()





        return binding.root
    }

    private fun pieChart() {
        binding.pieChart.slices = listOf(
            PieChart.Slice(
                0.30f,
                Color.rgb(120, 181, 0),
                Color.rgb(149, 224, 0),
                legend = "Food"
            ),
            PieChart.Slice(
                0.20f,
                Color.rgb(204, 168, 0),
                Color.rgb(249, 228, 0),
                legend = "Transportation"
            ),
            PieChart.Slice(
                0.17f,
                Color.rgb(255, 4, 4),
                Color.rgb(255, 72, 86),
                legend = "Entertainment"
            ),
            PieChart.Slice(
                0.20f,
                Color.rgb(0, 162, 216),
                Color.rgb(31, 199, 255),
                legend = "Others"
            ),
            PieChart.Slice(
                0.13f,
                Color.rgb(160, 165, 170),
                Color.rgb(175, 180, 185),
                legend = "Available"
            ),
        )
    }


    private fun recyclerViewSetupTransaction() {
        recyclerViewTransactionList = binding.recyclerViewTransactions
        transactionAdapter = TransactionAdapter(requireActivity(), ArrayList<TransactionModel>())
        recyclerViewTransactionList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        transactionViewModel.getAllTransaction(requireActivity())
            .observe(requireActivity(), Observer {
                transactionAdapter.setData(it as ArrayList<TransactionModel>)
            })
    }

    private fun recyclerViewSetupWallet() {
        recyclerViewWalletList = binding.recyclerViewWallet
        walletAdapter = WalletHomeScreenAdapter(requireActivity(), ArrayList<WalletModel>())
        recyclerViewWalletList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = walletAdapter
        }
        walletViewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        walletViewModel.getAllWallet(requireActivity()).observe(requireActivity(), Observer {
            walletAdapter.setData(it as ArrayList<WalletModel>)
        })
    }

    private fun goToAddEntryActivity() {
        binding.btnAddEntryActivity.setOnClickListener {
            startActivity(Intent(context, AddEntryActivity::class.java))
        }
    }
//    private fun sampleDataTransaction() {
//        dataSetTransaction = listOf(
//            TransactionModel(1, 15.50, 1, "18:30 15 Oct, 23", "Groceries", "Cash", null),
//            TransactionModel(2, 25.80, 2, "08:45 22 Sep, 23", "Income", "Cash", null),
//            TransactionModel(3, 30.25, 0, "14:20 05 Nov, 23", "Transfer", "Cash", "bKash"),
//            TransactionModel(4, 12.75, 1, "20:15 10 Oct, 23", "Books", "bKash", null),
//            TransactionModel(5, 18.90, 2, "12:40 03 Sep, 23", "Uber Income", "Cash", null),
//            TransactionModel(6, 22.40, 1, "09:55 08 Nov, 23", "Electronics", "Cash", null),
//            TransactionModel(7, 16.70, 0, "16:10 12 Sep, 23", "Transfer", "Cash", "Dbbl"),
//            TransactionModel(8, 14.20, 1, "22:30 30 Oct, 23", "Clothing", "Bkash", null),
//            TransactionModel(9, 28.60, 1, "11:05 18 Sep, 23", "Books", "Cash", null),
//            TransactionModel(10, 20.30, 2, "19:45 25 Oct, 23", "Salary", "Cash", null),
//        )
//    }
}
