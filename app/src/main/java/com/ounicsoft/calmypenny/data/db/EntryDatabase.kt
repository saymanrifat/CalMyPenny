package com.ounicsoft.calmypenny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.ui.utils.Constants.SQLITE_FILE_NAME
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.dao.EntryDao

@Database(entities = [EntryModel::class], version = 1)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun getEntryDao(): EntryDao

    companion object {
        private var INSTANCE: EntryDatabase? = null

        fun accessToDatabase(context: Context): EntryDatabase? {
            if (INSTANCE == null) {
                synchronized(EntryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EntryDatabase::class.java,
                        SQLITE_FILE_NAME
                    ).createFromAsset(SQLITE_FILE_NAME).build()
                }
            }
            return INSTANCE
        }
    }
}