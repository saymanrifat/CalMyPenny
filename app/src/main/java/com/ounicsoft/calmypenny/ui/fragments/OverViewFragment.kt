package com.ounicsoft.calmypenny.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.databinding.FragmentOverViewBinding
import com.ounicsoft.calmypenny.ui.activity.AddEntryActivity
import com.ounicsoft.calmypenny.ui.adapter.TransactionAdapter
import ir.mahozad.android.PieChart

class OverViewFragment : Fragment() {
    private lateinit var binding: FragmentOverViewBinding
    private lateinit var dataSetTransaction: List<EntryModel>
    private lateinit var transactionAdapter: TransactionAdapter
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

    private fun sampleDataTransaction() {
        dataSetTransaction = listOf(
            EntryModel(1, 15.50, 1, "18:30 15 Oct, 2023", "Groceries"),
            EntryModel(2, 25.80, 2, "08:45 22 Sep, 2023", "Electronics"),
            EntryModel(3, 30.25, 0, "14:20 05 Nov, 2023", "Clothing"),
            EntryModel(4, 12.75, 1, "20:15 10 Oct, 2023", "Books"),
            EntryModel(5, 18.90, 2, "12:40 03 Sep, 2023", "Home Decor"),
            EntryModel(6, 22.40, 1, "09:55 08 Nov, 2023", "Electronics"),
            EntryModel(7, 16.70, 0, "16:10 12 Sep, 2023", "Groceries"),
            EntryModel(8, 14.20, 1, "22:30 30 Oct, 2023", "Clothing"),
            EntryModel(9, 28.60, 1, "11:05 18 Sep, 2023", "Books"),
            EntryModel(10, 20.30, 2, "19:45 25 Oct, 2023", "Home Decor"),
        )
    }

    private fun recyclerViewSetupTransaction() {
        sampleDataTransaction()
        val recyclerViewTransactions = binding.recyclerViewTransactions
        recyclerViewTransactions.layoutManager = LinearLayoutManager(context)
        transactionAdapter = TransactionAdapter(dataSetTransaction)
        recyclerViewTransactions.adapter = transactionAdapter
    }


    private fun recyclerViewSetupWallet() {

    }


    private fun goToAddEntryActivity() {
        binding.btnAddEntryActivity.setOnClickListener {
            startActivity(Intent(context, AddEntryActivity::class.java))
        }
    }
}
