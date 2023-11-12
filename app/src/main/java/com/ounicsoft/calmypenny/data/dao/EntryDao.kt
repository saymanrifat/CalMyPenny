package com.ounicsoft.calmypenny.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ounicsoft.calmypenny.ui.utils.Constants
import com.ounicsoft.calmypenny.data.model.EntryModel

@Dao
interface EntryDao {
    @Query("SELECT * FROM ${Constants.DB_TRANSACTION_TABLE_NAME}")
    suspend fun allEntry(): List<EntryModel>

    @Insert
    suspend fun addEntry(transaction: EntryModel)

    @Update
    suspend fun updateEntry(transaction: EntryModel)

    @Delete
    suspend fun deleteEntry(transaction: EntryModel)

    @Query("SELECT * FROM ${Constants.DB_TRANSACTION_TABLE_NAME} WHERE type like '%' || :searchType || '%'")
    suspend fun searchType(searchType: String): List<EntryModel>


    @Query("SELECT count(*) FROM ${Constants.DB_TRANSACTION_TABLE_NAME} WHERE amount=:amount")
    suspend fun controlEntry(amount: String): Int
}