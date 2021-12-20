package com.tsato.mobile.sqldelighttest.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract val itemDao: ItemDao

    companion object {
        const val DATABASE_NAME = "test.db"
    }

}