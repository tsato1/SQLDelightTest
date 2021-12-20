package com.tsato.mobile.sqldelighttest.presentation

import com.tsato.mobile.sqldelighttest.data.Category

data class CategoryListState(
    val categoryList: List<Category> = emptyList()
)