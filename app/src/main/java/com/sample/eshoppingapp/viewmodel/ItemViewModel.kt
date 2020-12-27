package com.sample.eshoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.eshoppingapp.model.Item
import com.sample.eshoppingapp.repository.ItemRepository

class ItemViewModel : ViewModel() {

    var itemLiveData: LiveData<List<Item>>? = null

    fun getItems(context: Context): LiveData<List<Item>>? {
        itemLiveData = ItemRepository.getItems(context)
        return itemLiveData
    }
}