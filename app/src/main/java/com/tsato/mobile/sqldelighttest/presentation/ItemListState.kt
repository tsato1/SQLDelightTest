package com.tsato.mobile.sqldelighttest.presentation

import com.tsato.mobile.sqldelighttest.data.Item
import testdb.ItemEntity

data class ItemListState(
    val itemList: List<ItemEntity> = emptyList()
)