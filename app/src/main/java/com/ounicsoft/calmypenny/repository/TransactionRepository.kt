package com.ounicsoft.calmypenny.repository


import android.content.Context
import androidx.lifecycle.LiveData
import com.ounicsoft.calmypenny.data.db.CalMyPennyDatabase
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.data.model.WalletModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TransactionRepository {

    companion object {
        private var transactionDatabase: CalMyPennyDatabase? = null

        private fun intialiseDB(context: Context): CalMyPennyDatabase? {
            return CalMyPennyDatabase.getInstance(context)!!
        }

        fun insert(context: Context, transactionModel: TransactionModel) {
            transactionDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                transactionDatabase!!.transactionDao().insert(transactionModel)
            }
        }

        fun delete(context: Context, transactionModel: TransactionModel) {
            transactionDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                transactionDatabase!!.transactionDao().delete(transactionModel)
            }
        }

        fun getAllTransaction(context: Context): LiveData<List<TransactionModel>> {
            transactionDatabase = intialiseDB(context)
            return transactionDatabase!!.transactionDao().getAllEntry()
        }
    }
}