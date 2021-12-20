package com.tsato.mobile.sqldelighttest.presentation

import com.tsato.mobile.sqldelighttest.data.Item

data class ItemListState(
    val itemList: List<Item> = emptyList()
)