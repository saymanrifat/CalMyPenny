package com.ounicsoft.calmypenny.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ounicsoft.calmypenny.model.Entry

@Dao
interface EntryDao {
    @Query("SELECT * FROM entrys")
    suspend fun allEntry(): List<Entry>

    @Insert
    suspend fun addEntry(person: Entry)

    @Update
    suspend fun updateEntry(person: Entry)

    @Delete
    suspend fun deleteEntry(person: Entry)

    @Query("SELECT * FROM entrys ORDER BY RANDOM() LIMIT 3")
    suspend fun randomEntry(): List<Entry>

    @Query("SELECT * FROM entrys WHERE transaction_type like '%' || :searchName || '%'")
    suspend fun searchType(searchName: String): List<Entry>

    @Query("SELECT * FROM entrys WHERE transaction_id =:transactionId")
    suspend fun getEntry(transactionId: Int): Entry

    @Query("SELECT count(*) FROM entrys WHERE amount=:amount")
    suspend fun controlEntry(amount: String): Int
}