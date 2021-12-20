package com.tsato.mobile.sqldelighttest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsato.mobile.sqldelighttest.domain.CategoryRepository
import com.tsato.mobile.sqldelighttest.domain.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _itemListState = mutableStateOf(ItemListState())
    val itemListState: State<ItemListState> = _itemListState

    private val _categoryListState = mutableStateOf(CategoryListState())
    val categoryListState: State<CategoryListState> = _categoryListState

    private var getItemsJob: Job? = null
    private var getCategoriesJob: Job? = null

    init {
        loadItems()
    }

    private fun loadItems() {
        getItemsJob?.cancel()
        getItemsJob = itemRepository.getItems()
            .onEach {
                _itemListState.value = itemListState.value.copy(
                    itemList = it
                )
            }
            .launchIn(viewModelScope)
        getCategoriesJob?.cancel()
        getCategoriesJob = categoryRepository.getCategories()
            .onEach {
                _categoryListState.value = categoryListState.value.copy(
                    categoryList = it
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.InsertItem -> {
                viewModelScope.launch {
                    itemRepository.insertItem(event.item)
                }
            }
            is MainEvent.InsertCategory -> {
                viewModelScope.launch {
                    categoryRepository.insertCategory(event.category)
                }
            }
        }
    }

}