package com.ounicsoft.calmypenny.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ounicsoft.calmypenny.ui.utils.Constants
import com.ounicsoft.calmypenny.data.model.EntryModel

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entryModel: EntryModel)

    @Delete
    suspend fun delete(entryModel: EntryModel)

    @Query("SELECT * from ${Constants.DB_TRANSACTION_TABLE_NAME}")
    fun getAllEntry(): LiveData<List<EntryModel>>

}