package com.ounicsoft.calmypenny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.data.dao.TransactionDao
import com.ounicsoft.calmypenny.data.dao.WalletDao
import com.ounicsoft.calmypenny.data.model.TransactionModel
import com.ounicsoft.calmypenny.data.model.WalletModel
import com.ounicsoft.calmypenny.ui.utils.Constants.SQLITE_FILE_NAME

@Database(entities = [WalletModel::class, TransactionModel::class], version = 1)
abstract class CalMyPennyDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao
    abstract fun entryDao(): TransactionDao

    companion object {
        @Volatile
        var instance: CalMyPennyDatabase? = null

        fun getInstance(context: Context): CalMyPennyDatabase? {
            if (instance == null) {
                synchronized(CalMyPennyDatabase::class.java)
                {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, CalMyPennyDatabase::class.java,
                            SQLITE_FILE_NAME
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