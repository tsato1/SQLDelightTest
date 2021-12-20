package com.tsato.mobile.sqldelighttest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.tsato.mobile.sqldelighttest.BuildConfig
import com.tsato.mobile.sqldelighttest.data.Category
import com.tsato.mobile.sqldelighttest.data.Item
import java.math.BigDecimal

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val itemList = viewModel.itemListState.value.itemList
    val categoryList = viewModel.categoryListState.value.categoryList

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Version Code = ${BuildConfig.VERSION_NAME}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(itemList.size) { index ->
                    val item = itemList[index]

                    Text(text = "1=${item.val2}")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(categoryList.size) { index ->
                    val category = categoryList[index]

                    Text(text = "2=${category.name}")
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Green),
                onClick = {
                    viewModel.onEvent(MainEvent.InsertItem(
                        Item(BigDecimal(110), "item110", "2020-09-02")
                    ))
                },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            )
            Button(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Black),
                onClick = {
                    viewModel.onEvent(MainEvent.InsertCategory(
                        Category(0, 1, "name", null))
                    )
                },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            )
        }
    }

}