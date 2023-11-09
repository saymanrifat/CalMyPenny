package com.ounicsoft.calmypenny.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ounicsoft.calmypenny.model.EntryModel

@Dao
interface EntryDao {
    @Query("SELECT * FROM entrys")
    suspend fun allEntry(): List<EntryModel>

    @Insert
    suspend fun addEntry(person: EntryModel)

    @Update
    suspend fun updateEntry(person: EntryModel)

    @Delete
    suspend fun deleteEntry(person: EntryModel)

    @Query("SELECT * FROM entrys ORDER BY RANDOM() LIMIT 3")
    suspend fun randomEntry(): List<EntryModel>

    @Query("SELECT * FROM entrys WHERE type like '%' || :searchType || '%'")
    suspend fun searchType(searchType: String): List<EntryModel>

    @Query("SELECT * FROM entrys WHERE transaction_id =:transactionId")
    suspend fun getEntry(transactionId: Int): EntryModel

    @Query("SELECT count(*) FROM entrys WHERE amount=:amount")
    suspend fun controlEntry(amount: String): Int
}