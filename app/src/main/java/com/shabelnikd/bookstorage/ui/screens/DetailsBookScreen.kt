package com.shabelnikd.bookstorage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shabelnikd.bookstorage.data.models.Book
import com.shabelnikd.bookstorage.ui.components.CenteredTopBar

@Composable
fun DetailsBookScreen(
    book: Book,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenteredTopBar(text = book.title, imageVector = androidx.compose.material.icons.Icons.AutoMirrored.Filled.ArrowBack) {
                onBackClick()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Автор: ${book.author}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Жанр: ${book.genre}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Год: ${book.year}", style = MaterialTheme.typography.titleMedium)
        }
    }
}