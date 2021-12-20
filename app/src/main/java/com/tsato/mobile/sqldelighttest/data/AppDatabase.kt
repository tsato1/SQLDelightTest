package com.tsato.mobile.sqldelighttest.data

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Item::class, Category::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract val itemDao: ItemDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "test.db"

        @VisibleForTesting
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("Test", "migration_1_2")
                database.execSQL("CREATE TABLE asdf (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "name TEXT NOT NULL DEFAULT ''" +
                        "bytes BLOB DEFAULT NULL);")
            }
        }
    }

}