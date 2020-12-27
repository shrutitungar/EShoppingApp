package com.sample.eshoppingapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.eshoppingapp.model.Item

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class EShopDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): DAOAccess

    companion object {
        @Volatile
        private var INSTANCE: EShopDatabase? = null

        fun getDatabaseClient(context: Context): EShopDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, EShopDatabase::class.java, "Eshop")
                    .createFromAsset("database/Items.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}