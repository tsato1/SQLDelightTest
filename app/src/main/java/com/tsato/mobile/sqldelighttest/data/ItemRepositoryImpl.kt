package com.tsato.mobile.sqldelighttest.data

import com.tsato.mobile.sqldelighttest.domain.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val dao: ItemDao
) : ItemRepository {

    override suspend fun insertItem(item: Item): Long {
        return dao.insertItem(item)
    }

    override fun getItems(): Flow<List<Item>> {
        return dao.getItems()
    }

    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item)
    }


}