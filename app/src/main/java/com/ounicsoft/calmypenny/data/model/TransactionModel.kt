package com.ounicsoft.calmypenny.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ounicsoft.calmypenny.ui.utils.Constants

@Entity(tableName = Constants.DB_TRANSACTION_TABLE_NAME)
data class TransactionModel(
    @PrimaryKey(autoGenerate = true) val transactionId: Int?,
    @ColumnInfo(name = Constants.AMOUNT) var amount: Double?,
    @ColumnInfo(name = Constants.TRANSACTION_TYPE) var transactionType: Int,  // (0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = Constants.TRANSACTION_TIME) var transactionTime: String?,
    @ColumnInfo(name = Constants.CATEGORY_CAUSE) var categoryCause: String?,
    @ColumnInfo(name = Constants.TRANSACTION_FROM) var transactionFrom: String?,
    @ColumnInfo(name = Constants.TRANSACTION_TO) var transactionTo: String?,
) : java.io.Serializable