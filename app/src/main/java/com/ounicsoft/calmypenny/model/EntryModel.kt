package com.ounicsoft.calmypenny.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrys")
data class EntryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id") var transactionId: Int,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "type") var transactionType: Int,  //(0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = "transaction_time") var transactionTime: String,
    @ColumnInfo(name = "transaction_type") var transactionCause: String,
)
