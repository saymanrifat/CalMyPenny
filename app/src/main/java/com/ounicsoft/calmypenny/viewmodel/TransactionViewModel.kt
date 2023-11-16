package com.ounicsoft.calmypenny.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.repository.TransactionRepository


class TransactionViewModel : ViewModel() {
    fun insert(context: Context, transactionModel: TransactionModel) {
        TransactionRepository.insert(context, transactionModel)
    }

    fun delete(context: Context, transactionModel: TransactionModel) {
        TransactionRepository.delete(context, transactionModel)
    }

    fun getAllTransaction(context: Context): LiveData<List<TransactionModel>> {
        return TransactionRepository.getAllTransaction(context)
    }
}