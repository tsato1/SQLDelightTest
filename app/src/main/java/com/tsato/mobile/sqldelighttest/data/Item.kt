package com.tsato.mobile.sqldelighttest.data

import androidx.annotation.NonNull
import androidx.room.*
import java.math.BigDecimal

@Entity(tableName = "items")
class Item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0
        private set

    @ColumnInfo(name = "val_1")
    @TypeConverters(Converters::class)
    @NonNull
    var val1: BigDecimal

    @ColumnInfo(name = "val_2", defaultValue = "===")
    var val2 = "==="
        private set

    @ColumnInfo(name = "update_date")
    var updateDate = ""
        private set

    constructor(
        id: Long,
        val1: BigDecimal,
        val2: String,
        updateDate: String) {
        this.id = id
        this.val1 = val1
        this.val2 = val2
        this.updateDate = updateDate
    }

    /* called from TabFragment1 before getting saved  */
    @Ignore
    constructor( // without id: id is auto-increment
        val1: BigDecimal,
        val2: String,
        updateDate: String) {
        this.val1 = val1
        this.val2 = val2
        this.updateDate = updateDate
    }

}