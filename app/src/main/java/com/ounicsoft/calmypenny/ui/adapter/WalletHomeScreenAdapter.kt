package com.ounicsoft.calmypenny.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.WalletCustomViewBinding


class WalletHomeScreenAdapter(private val dataSet: List<WalletModel>) :
    RecyclerView.Adapter<WalletHomeScreenAdapter.ViewHolder>() {

    class ViewHolder(binding: WalletCustomViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val walletName: TextView
        val amount: TextView
        val cardView: CardView

        init {
            walletName = binding.walletName
            amount = binding.tAmount
            cardView = binding.cardView
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = WalletCustomViewBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(binding: ViewHolder, position: Int) {
        val data = dataSet[position]
        binding.walletName.text = data.walletName
        binding.amount.text = data.totalAmount.toString()
        binding.cardView.setCardBackgroundColor(data.walletColor)
    }

    override fun getItemCount() = dataSet.size
}
