package com.shabelnikd.bookstorage.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.shabelnikd.bookstorage.data.models.Book
import com.shabelnikd.bookstorage.ui.screens.DetailsBookScreen
import com.shabelnikd.bookstorage.ui.screens.HomeScreen

@Composable
fun AppNavigation(exit: () -> Unit) {
    var currentScreenState by remember { mutableStateOf<Screen>(Screen.Home) }

    val currentScreen = currentScreenState

    val books = remember { Book.getStaticData() }

    when (currentScreen) {
        Screen.Home -> {
            HomeScreen(
                books = books,
                onBookClick = { book ->
                    currentScreenState = Screen.DetailsBook(book)
                },
                onExitClick = {
                    exit()
                }
            )
        }

        is Screen.DetailsBook -> {
            DetailsBookScreen(
                book = currentScreen.book,
                onBackClick = {
                    currentScreenState = Screen.Home
                }
            )
        }
    }
}