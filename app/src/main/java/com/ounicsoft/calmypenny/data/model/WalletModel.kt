package com.ounicsoft.calmypenny.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ounicsoft.calmypenny.ui.utils.Constants

@Entity(tableName = Constants.DB_WALLET_TABLE_NAME)
data class WalletModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.WALLET_ID) var walletID: Int,
    @ColumnInfo(name = Constants.WALLET_NAME) var walletName: String,
    @ColumnInfo(name = Constants.TOTAL_AMOUNT) var totalAmount: Double,  // (0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = Constants.WALLET_COLOR) var walletColor: Int,
)