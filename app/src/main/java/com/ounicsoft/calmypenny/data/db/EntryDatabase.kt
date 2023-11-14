package com.ounicsoft.calmypenny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.ui.utils.Constants.SQLITE_FILE_NAME
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.data.dao.EntryDao
import com.ounicsoft.calmypenny.ui.utils.Constants.DB_TRANSACTION_TABLE_NAME

@Database(entities = arrayOf(EntryModel::class), version = 1)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun getEntryDao(): EntryDao

    companion object {
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getDatabase(context: Context): EntryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    SQLITE_FILE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}

