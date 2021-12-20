package com.tsato.mobile.sqldelighttest.data

import com.tsato.mobile.sqldelighttest.domain.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun insertCategory(category: Category): Long {
        return dao.insertCategory(category)
    }

    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategories()
    }


}