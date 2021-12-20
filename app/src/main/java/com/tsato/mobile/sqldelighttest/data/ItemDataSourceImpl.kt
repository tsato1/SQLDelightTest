package com.tsato.mobile.sqldelighttest.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.tsato.mobile.sqldelighttest.TestDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import testdb.ItemEntity

class ItemDataSourceImpl(
    db: TestDatabase // coming from the definition in build.gradle file at the very bottom
) : ItemDataSource {

    private val queries = db.itemEntityQueries

    override suspend fun getItemById(id: Long): ItemEntity? {
        return withContext(Dispatchers.IO) { // todo: Inject dispatcher
            queries.getItemById(id).executeAsOneOrNull()
        }
    }

    override fun getItems(): Flow<List<ItemEntity>> {
        return queries.getItems().asFlow().mapToList()
    }

    override suspend fun insertItem(item: Item): Long {
        withContext(Dispatchers.IO) {
            queries.insertItem(null, item.val1.toLong(), item.val2, item.updateDate)
//            lastInsertedRowId = dbQuery.selectLastInsertedRowId().executeAsOne()
        }
        return 1L
    }

    override suspend fun deleteItem(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteItem(id)
        }
    }
}