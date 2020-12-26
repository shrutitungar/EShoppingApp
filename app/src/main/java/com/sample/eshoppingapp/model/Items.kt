package com.sample.eshoppingapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Items(

    @ColumnInfo(name = "name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "price")
    @NonNull
    var price: Double,

    @ColumnInfo(name = "gst_percentage")
    @NonNull
    var gstPercentage: Double,

    @ColumnInfo(name = "image_url")
    @NonNull
    var imageUrl: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id: Int? = null
}
