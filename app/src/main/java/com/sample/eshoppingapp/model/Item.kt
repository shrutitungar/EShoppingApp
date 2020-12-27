package com.sample.eshoppingapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Item(

    @ColumnInfo(name = "name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "price")
    @NonNull
    var price: Double,

    @ColumnInfo(name = "gstPercentage")
    @NonNull
    var gstPercentage: Double,

    @ColumnInfo(name = "imageUrl")
    @NonNull
    var imageUrl: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id: Int? = null

    @Ignore
    var quantity: Int = 0
}
