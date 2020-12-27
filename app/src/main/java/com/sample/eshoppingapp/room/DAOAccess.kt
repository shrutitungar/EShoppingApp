package com.sample.eshoppingapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sample.eshoppingapp.model.Item

@Dao
interface DAOAccess {

    @Query("SELECT * FROM Items")
    fun getItemDetails(): LiveData<List<Item>>
}