package com.ounicsoft.calmypenny.data.repository

import androidx.lifecycle.LiveData
import com.ounicsoft.calmypenny.data.dao.EntryDao
import com.ounicsoft.calmypenny.data.model.EntryModel

class EntryRepository(private val entryDao: EntryDao) {

    val allTodos: LiveData<List<EntryModel>> = entryDao.getAllEntry()

    suspend fun insert(entryModel: EntryModel) {
        entryDao.insert(entryModel)
    }

    suspend fun delete(entryModel: EntryModel) {
        entryDao.delete(entryModel)
    }
}