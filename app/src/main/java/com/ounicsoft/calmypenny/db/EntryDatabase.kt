package com.ounicsoft.calmypenny.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.model.Entry
import com.ounicsoft.calmypenny.dao.EntryDao

@Database(entities = [Entry::class], version = 1)
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
                        "contact.sqlite"
                    ).createFromAsset("contact.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}