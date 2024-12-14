package com.example.listify

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ItemDao = AppDatabase.getDatabase(application).itemDao()
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    fun insert(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }
    fun delete(item: Item) = viewModelScope.launch {
        itemDao.delete(item)
    }

}
