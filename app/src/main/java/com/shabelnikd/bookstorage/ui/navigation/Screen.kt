package com.shabelnikd.bookstorage.ui.navigation

import com.shabelnikd.bookstorage.data.models.Book

sealed class Screen() {
    object Home : Screen()
    data class DetailsBook(val book: Book) : Screen()
}