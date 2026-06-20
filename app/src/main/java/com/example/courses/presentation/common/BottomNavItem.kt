package com.example.courses.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val name: String, val icon: ImageVector) {
    object Home: BottomNavItem("Главная", Icons.Default.Home)
    object Favourite: BottomNavItem("Избранное", Icons.Default.BookmarkBorder)
    object Account: BottomNavItem("Аккаунт", Icons.Default.PersonOutline)
}