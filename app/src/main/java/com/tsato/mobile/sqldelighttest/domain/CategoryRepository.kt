package com.tsato.mobile.sqldelighttest.domain

import com.tsato.mobile.sqldelighttest.data.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun insertCategory(category: Category): Long

    fun getCategories(): Flow<List<Category>>

}