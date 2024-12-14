package com.example.listify

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<Item>>

    @Delete
    suspend fun delete(item: Item)

}
