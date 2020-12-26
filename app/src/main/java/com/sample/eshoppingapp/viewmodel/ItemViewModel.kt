package com.sample.eshoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.eshoppingapp.model.Items
import com.sample.eshoppingapp.repository.ItemRepository

class ItemViewModel : ViewModel() {

    var itemLiveData: LiveData<List<Items>>? = null

    fun getItems(context: Context): LiveData<List<Items>>? {
        itemLiveData = ItemRepository.getItems(context)
        return itemLiveData
    }
}