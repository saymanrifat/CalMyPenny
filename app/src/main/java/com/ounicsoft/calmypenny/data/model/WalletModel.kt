package com.ounicsoft.calmypenny.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ounicsoft.calmypenny.ui.utils.Constants

@Entity(tableName = Constants.DB_WALLET_TABLE_NAME)
data class WalletModel(
    @PrimaryKey(autoGenerate = true) val walletId: Int?,
    @ColumnInfo(name = Constants.WALLET_NAME) val walletName: String?,
    @ColumnInfo(name = Constants.TOTAL_AMOUNT) val totalAmount: Double?,  // (0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = Constants.WALLET_COLOR) val walletColor: Int?,
) : java.io.Serializable