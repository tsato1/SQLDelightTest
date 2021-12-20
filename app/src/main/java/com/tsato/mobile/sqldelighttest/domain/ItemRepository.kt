package com.tsato.mobile.sqldelighttest.domain

import com.tsato.mobile.sqldelighttest.data.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun insertItem(item: Item): Long

    fun getItems(): Flow<List<Item>>

    suspend fun deleteItem(item: Item)

}