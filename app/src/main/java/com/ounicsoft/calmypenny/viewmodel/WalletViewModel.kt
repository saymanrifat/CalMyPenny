package com.ounicsoft.calmypenny.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.repository.WalletRepository

class WalletViewModel : ViewModel() {

    fun insert(context: Context, walletModel: WalletModel) {
        WalletRepository.insert(context, walletModel)
    }

    fun delete(context: Context, walletModel: WalletModel) {
        WalletRepository.delete(context, walletModel)
    }

    fun update(context: Context, walletModel: WalletModel) {
        WalletRepository.update(context, walletModel)
    }

    fun getAllWallet(context: Context): LiveData<List<WalletModel>> {
        return WalletRepository.getAllWallet(context)
    }

    fun getAllWalletName(context: Context): LiveData<List<String>> {
        return WalletRepository.getAllWalletName(context)
    }
}