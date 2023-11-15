package com.ounicsoft.calmypenny.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.WalletListItemCustomBinding

class WalletHomeScreenAdapter(
    private val context: Context,
    var walletList: ArrayList<WalletModel>,
//    val listener: WalletClickListener
) :
    RecyclerView.Adapter<WalletHomeScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WalletHomeScreenAdapter.ViewHolder {
        val binding =
            WalletListItemCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = walletList[position]
        holder.walletName.text = item.walletName
//        holder.layoutBtn.setOnClickListener {
//            listener.onItemClicked(walletList[holder.adapterPosition])
//        }
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    fun setData(userList: ArrayList<WalletModel>) {
        this.walletList = userList
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: WalletListItemCustomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val walletName = binding.walletName
        val layoutBtn = binding.layoutBtn
    }

//    interface WalletClickListener {
//        fun onItemClicked(walletEntry: WalletModel)
//    }
}