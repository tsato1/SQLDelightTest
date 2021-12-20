package com.tsato.mobile.sqldelighttest.data

import kotlinx.coroutines.flow.Flow
import testdb.ItemEntity

/*
just like dao in room
 */
interface ItemDataSource {

    suspend fun getItemById(id: Long): ItemEntity?

    fun getItems(): Flow<List<ItemEntity>>

    suspend fun insertItem(item: Item): Long

    suspend fun deleteItem(id: Long)

}