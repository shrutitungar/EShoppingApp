package com.sample.eshoppingapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sample.eshoppingapp.model.Item
import com.sample.eshoppingapp.room.EShopDatabase

class ItemRepository {

    companion object {
        var database: EShopDatabase? = null

        var item: LiveData<List<Item>>? = null

        fun initializeDB(context: Context): EShopDatabase {
            return EShopDatabase.getDatabaseClient(context)
        }

        fun getItems(context: Context): LiveData<List<Item>>? {
            database = initializeDB(context)
            item = database!!.getShoppingDao().getItemDetails()
            return item
        }
    }
}