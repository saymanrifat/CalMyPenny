package com.ounicsoft.calmypenny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.data.dao.WalletDao
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.ui.utils.Constants.WALLET_SQLITE_FILE_NAME

@Database(entities = [WalletModel::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao

    companion object {
        @Volatile
        var instance: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase? {
            if (instance == null) {
                synchronized(WalletDatabase::class.java)
                {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, WalletDatabase::class.java,
                            WALLET_SQLITE_FILE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }
}