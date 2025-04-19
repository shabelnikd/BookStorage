package com.shabelnikd.bookstorage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shabelnikd.bookstorage.data.models.Book
import com.shabelnikd.bookstorage.ui.components.BookListItem
import com.shabelnikd.bookstorage.ui.components.CenteredTopBar

@Composable
fun HomeScreen(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    onExitClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf("Все") }


    val genres = remember(books) {
        linkedSetOf("Все").apply { addAll(books.map { it.genre }.distinct().sorted()) }.toList()
    }


    val filteredBooks by remember(books, selectedGenre, searchQuery) {
        derivedStateOf {
            books.filter { book ->
                val matchesGenre = selectedGenre == "Все" || book.genre == selectedGenre
                val matchesSearch = searchQuery.isBlank() ||
                        book.title.contains(searchQuery, ignoreCase = true) ||
                        book.author.contains(searchQuery, ignoreCase = true) ||
                        book.year.toString().contains(searchQuery, ignoreCase = true)
                matchesGenre && matchesSearch
            }
        }
    }


    Scaffold(
        topBar = {
            CenteredTopBar(
                text = "Geeks Homework 2",
                imageVector = Icons.AutoMirrored.Filled.ExitToApp
            ) {
                onExitClick()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Поиск по названию или автору") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Поиск") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true,
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(genres) { genre ->
                    FilterChip(
                        selected = selectedGenre == genre,
                        onClick = { selectedGenre = genre },
                        label = { Text(genre) }
                    )
                }
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(filteredBooks) { book ->
                    BookListItem(book = book, onClick = { onBookClick(book) })
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.DarkGray
                    )
                }

                if (filteredBooks.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Книги не найдены")
                        }
                    }
                }
            }
        }
    }
}