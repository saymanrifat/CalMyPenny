package com.ounicsoft.calmypenny.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.databinding.TransactionListViewBinding

class TransactionAdapter(private val dataSet: List<TransactionModel>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(binding: TransactionListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var time: TextView
        var cause: TextView
        var amount: TextView
        var from: TextView
        var to: TextView
        var toLayout: LinearLayout

        init {
            time = binding.time
            cause = binding.cause
            amount = binding.amount
            from = binding.from
            to = binding.to
            toLayout = binding.transferToLayout
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransactionListViewBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]
        val transactionType = data.transactionType
        viewHolder.time.text = data.transactionTime
        viewHolder.cause.text = data.categoryCause
        viewHolder.from.text = data.transactionFrom
        if (data.transactionTo != null) {
            viewHolder.to.text = data.transactionTo
            viewHolder.toLayout.visibility = View.VISIBLE
        }



        viewHolder.amount.text =
            "${transactionTypeSign(data.transactionType)} ${data.amount.toString()}"
        viewHolder.amount.setTextColor(transactionTypeColor(transactionType))
    }

    override fun getItemCount() = dataSet.size

    private fun transactionTypeColor(int: Int): Int = when (int) {
        1 -> Color.RED // (Expense)
        0 -> Color.BLACK // (Transfer)
        2 -> Color.GREEN // (Income)
        else -> 0
    }

    private fun transactionTypeSign(int: Int): String = when (int) {
        1 -> "-" // (Expense)
        0 -> "=" // (Transfer)
        2 -> "+" // (Income)
        else -> "null"
    }
}
