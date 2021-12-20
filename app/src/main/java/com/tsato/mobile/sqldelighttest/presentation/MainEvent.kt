package com.tsato.mobile.sqldelighttest.presentation

import com.tsato.mobile.sqldelighttest.data.Category
import com.tsato.mobile.sqldelighttest.data.Item

sealed class MainEvent {
    data class InsertItem(val item: Item) : MainEvent()
    data class InsertCategory(val category: Category) : MainEvent()
}