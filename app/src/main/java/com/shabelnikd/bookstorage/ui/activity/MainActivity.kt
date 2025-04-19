package com.shabelnikd.bookstorage.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shabelnikd.bookstorage.ui.navigation.AppNavigation
import com.shabelnikd.bookstorage.ui.theme.BookStorageTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookStorageTheme {
                AppNavigation(exit = {
                    finish()
                })
            }
        }
    }
}


