package com.ounicsoft.calmypenny.ui.utils

object Constants {
    // Room Database
    const val SQLITE_FILE_NAME = "calmypenny_sqlite"

    // Transaction Key
    const val DB_TRANSACTION_TABLE_NAME = "transactions_table"
    const val TRANSACTION_ID = "transactionId"
    const val AMOUNT = "amount"
    const val TRANSACTION_TYPE = "transaction_type" // (0=Transfer, 1= Minus, 2= Plus)
    const val TRANSACTION_TIME = "transaction_time"
    const val TRANSACTION_FROM = "transaction_from"
    const val TRANSACTION_TO = "transaction_to"
    const val CATEGORY_CAUSE = "transaction_category_cause"

    // Wallet Key
    const val DB_WALLET_TABLE_NAME = "wallet_table"
    const val WALLET_ID = "walletId"
    const val WALLET_NAME = "wallet_name"
    const val TOTAL_AMOUNT = "total_amount"
    const val WALLET_COLOR = "wallet_color"
}