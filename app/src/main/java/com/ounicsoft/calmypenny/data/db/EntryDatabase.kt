package com.ounicsoft.calmypenny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ounicsoft.calmypenny.ui.utils.Constants.SQLITE_FILE_NAME
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.data.dao.EntryDao
import com.ounicsoft.calmypenny.ui.utils.Constants.DB_TRANSACTION_TABLE_NAME

@Database(entities = [EntryModel::class], version = 1, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    // a boiler plate code to instantiate room database
    companion object {
        @Volatile
        private var instance: EntryDatabase? = null
        fun getInstance(context: Context): EntryDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext, EntryDatabase::class.java, SQLITE_FILE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}