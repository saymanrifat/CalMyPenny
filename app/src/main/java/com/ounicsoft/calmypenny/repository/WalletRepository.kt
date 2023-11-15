package com.ounicsoft.calmypenny.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ounicsoft.calmypenny.data.db.CalMyPennyDatabase
import com.ounicsoft.calmypenny.data.model.WalletModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WalletRepository {

    companion object {
        private var walletDatabase: CalMyPennyDatabase? = null

        private fun intialiseDB(context: Context): CalMyPennyDatabase? {
            return CalMyPennyDatabase.getInstance(context)!!
        }

        fun insert(context: Context, walletModel: WalletModel) {
            walletDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                walletDatabase!!.walletDao().insert(walletModel)
            }
        }

        fun delete(context: Context, walletModel: WalletModel) {
            walletDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                walletDatabase!!.walletDao().delete(walletModel)
            }
        }

        fun update(context: Context, walletModel: WalletModel) {
            walletDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                walletDatabase!!.walletDao()
                    .update(walletModel.walletId, walletModel.walletName, walletModel.totalAmount)
            }
        }

        fun getAllWallet(context: Context): LiveData<List<WalletModel>> {
            walletDatabase = intialiseDB(context)
            return walletDatabase!!.walletDao().getAllWallet()
        }
    }
}