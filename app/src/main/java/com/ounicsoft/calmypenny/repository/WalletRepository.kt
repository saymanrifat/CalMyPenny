package com.ounicsoft.calmypenny.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ounicsoft.calmypenny.data.db.WalletDatabase
import com.ounicsoft.calmypenny.data.model.WalletModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WalletRepository {

    companion object {
        var walletDatabase: WalletDatabase? = null

        private fun intialiseDB(context: Context): WalletDatabase? {
            return WalletDatabase.getInstance(context)!!
        }

        fun insert(context: Context, walletModel: WalletModel) {
            walletDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                walletDatabase!!.walletDao().insert(walletModel)
            }
        }

        fun getAllWallet(context: Context): LiveData<List<WalletModel>> {
            walletDatabase = intialiseDB(context)
            return walletDatabase!!.walletDao().getAllWallet()
        }
    }
}