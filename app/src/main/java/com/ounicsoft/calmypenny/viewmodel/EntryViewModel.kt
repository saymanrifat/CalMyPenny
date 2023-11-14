package com.ounicsoft.calmypenny.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ounicsoft.calmypenny.data.db.EntryDatabase
import com.ounicsoft.calmypenny.data.model.EntryModel
import com.ounicsoft.calmypenny.data.repository.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EntryRepository
    val allEntry: LiveData<List<EntryModel>>

    init {
        val dao = EntryDatabase.getDatabase(application).getEntryDao()
        repository = EntryRepository(dao)
        allEntry = repository.allTodos
    }

    fun insertTodo(entryModel: EntryModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entryModel)
    }

    fun deleteTodo(entryModel: EntryModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entryModel)
    }
}