package com.shabelnikd.bookstorage.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shabelnikd.bookstorage.data.models.Book


@Composable
fun BookListItem(book: Book, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = book.title, style = MaterialTheme.typography.titleMedium)
        Text(text = book.author, style = MaterialTheme.typography.bodyMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = book.genre, style = MaterialTheme.typography.bodySmall)
            Text(text = "(${book.year} год)", style = MaterialTheme.typography.bodySmall)
        }
    }
}