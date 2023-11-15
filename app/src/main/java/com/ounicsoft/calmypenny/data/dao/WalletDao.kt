package com.ounicsoft.calmypenny.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.ui.utils.Constants

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(walletModel: WalletModel)

    @Delete
    suspend fun delete(walletModel: WalletModel)

//    @Query("UPDATE ${Constants.DB_WALLET_TABLE_NAME} set title = :title, note = :note where id = :id")
//    suspend fun update(id: Int?, title: String?, note: String?)

    @Query("SELECT * from ${Constants.DB_WALLET_TABLE_NAME}")
    fun getAllWallet(): LiveData<List<WalletModel>>

}