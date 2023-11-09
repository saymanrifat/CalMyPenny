package com.ounicsoft.calmypenny.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "entrys")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id") @NotNull var transactionId: Int,
    @ColumnInfo(name = "amount") @NotNull var amount: Double,
    @ColumnInfo(name = "transaction_type") @NotNull var transactionType: Int,  //(0=Transfer, 1= Minus, 2= Plus)
    @ColumnInfo(name = "transaction_time") @NotNull var transactionTime: String,
    @ColumnInfo(name = "transaction_cause") @NotNull var transactionCause: String,
)
