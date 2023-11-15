package com.ounicsoft.calmypenny.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.databinding.WalletListItemCustomBinding

class WalletListAdapter(
    private val context: Context,
    var walletList: ArrayList<WalletModel>,
    val listener: WalletClickListener
) :
    RecyclerView.Adapter<WalletListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WalletListAdapter.ViewHolder {
        val binding =
            WalletListItemCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = walletList[position]
        holder.walletName.text = item.walletName
        holder.tvBalance.text = item.totalAmount.toString()
        holder.layoutBtn.setOnClickListener() {
            listener.onClick(walletList[holder.adapterPosition])
        }
        holder.layoutBtn.setOnLongClickListener {
            listener.onLongClick(walletList[holder.adapterPosition])
            true
        }
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
        val tvBalance = binding.tvBalance
    }

    interface WalletClickListener {

        fun onClick(walletEntry: WalletModel)
        fun onLongClick(walletEntry: WalletModel)
    }
}