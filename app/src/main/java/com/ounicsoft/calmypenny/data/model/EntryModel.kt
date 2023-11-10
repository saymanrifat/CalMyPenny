package com.ounicsoft.calmypenny.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ounicsoft.calmypenny.ui.utils.Constants

@Entity(tableName = Constants.DB_TRANSACTION_TABLE_NAME)
data class EntryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TRANSACTION_ID) var transactionId: Int,
    @ColumnInfo(name = Constants.AMOUNT) var amount: Double,
    @ColumnInfo(name = Constants.TYPE) var type: Int,  // (0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = Constants.TRANSACTION_TIME) var transactionTime: String,
    @ColumnInfo(name = Constants.CAUSE) var cause: String,
)
