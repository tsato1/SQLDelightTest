package com.tsato.mobile.sqldelighttest.data

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
class Category(
    id: Long,
    @ColumnInfo(name = "code", defaultValue = "0") var code: Int,
    @ColumnInfo(name = "name", defaultValue = "") var name: String,
    @Nullable @ColumnInfo(name = "bytes") var image: ByteArray?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Long = id
        private set

}