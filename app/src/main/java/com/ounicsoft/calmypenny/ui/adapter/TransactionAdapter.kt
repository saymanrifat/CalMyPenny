package com.ounicsoft.calmypenny.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.databinding.TransactionCustomViewBinding

class TransactionAdapter(private val dataSet: List<EntryModel>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(binding: TransactionCustomViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var time: TextView
        var cause: TextView
        var amount: TextView

        init {
            time = binding.time
            cause = binding.type
            amount = binding.amount
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransactionCustomViewBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]
        val transactionType = data.type
        viewHolder.time.text = data.transactionTime
        viewHolder.cause.text = data.category
        viewHolder.amount.text = "${transactionTypeSign(data.type)} ${data.amount.toString()}"
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
