package com.tsato.mobile.sqldelighttest.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val itemList = viewModel.itemListState.value.itemList

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList.size) { index ->
            val item = itemList[index]

            Text(text = "val1=${item.val1}, val2=${item.val2} date=${item.updateDate}")
        }
    }

}