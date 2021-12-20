package com.tsato.mobile.sqldelighttest.data

import com.tsato.mobile.sqldelighttest.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import testdb.ItemEntity

class ItemRepositoryImpl(
//    private val dao: ItemDao
    private val dataSource: ItemDataSource
) : ItemRepository {

    override suspend fun insertItem(item: Item): Long {
//        return dao.insertItem(item)
        return dataSource.insertItem(item)
    }

    override fun getItems(): Flow<List<ItemEntity>> {
//        return dao.getItems()
        return dataSource.getItems()
    }

    override suspend fun deleteItem(item: Item) {
//        dao.deleteItem(item)
        return dataSource.deleteItem(item.id)
    }


}