package com.tsato.mobile.sqldelighttest.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsato.mobile.sqldelighttest.data.Item
import com.tsato.mobile.sqldelighttest.domain.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _itemListState = mutableStateOf(ItemListState())
    val itemListState: State<ItemListState> = _itemListState

    private var getItemsJob: Job? = null

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
    }

}