package com.ounicsoft.calmypenny.ui.utils

object Constants {
    // Room Database
    const val SQLITE_FILE_NAME = "transaction.sqlite"

    // Transaction Key
    const val DB_TRANSACTION_TABLE_NAME = "entrys"
    const val TRANSACTION_ID = "transaction_id"
    const val AMOUNT = "amount"
    const val TYPE = "type" // (0=Transfer, 1= Minus, 2= Plus)
    const val TRANSACTION_TIME = "transaction_time"
    const val CAUSE = "transaction_type"

    // Wallet Key
    const val DB_WALLET_TABLE_NAME = "transaction_time"
    const val WALLET_ID = "wallet_id"
    const val WALLET_NAME = "wallet_name"
    const val TOTAL_AMOUNT = "total_amount"
    const val WALLET_COLOR = "wallet_color"
}