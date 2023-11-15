package com.ounicsoft.calmypenny.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.WalletCustomViewBinding

class WalletHomeScreenAdapter(
    private val context: Context,
    private var walletList: ArrayList<WalletModel>,
) :
    RecyclerView.Adapter<WalletHomeScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WalletHomeScreenAdapter.ViewHolder {
        val binding =
            WalletCustomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = walletList[position]
        holder.walletName.text = item.walletName
        holder.walletBalance.text = item.totalAmount.toString()
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    fun setData(walletList: ArrayList<WalletModel>) {
        this.walletList = walletList
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: WalletCustomViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val walletName = binding.walletName
        val walletBalance = binding.tAmount
    }
}