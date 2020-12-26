package com.sample.eshoppingapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sample.eshoppingapp.model.Items
import com.sample.eshoppingapp.room.EShopDatabase

class ItemRepository {

    companion object {
        var database: EShopDatabase? = null

        var items: LiveData<List<Items>>? = null

        fun initializeDB(context: Context): EShopDatabase {
            return EShopDatabase.getDatabaseClient(context)
        }

        fun getItems(context: Context): LiveData<List<Items>>? {
            database = initializeDB(context)
            items = database!!.getShoppingDao().getItemDetails()
            return items
        }
    }
}