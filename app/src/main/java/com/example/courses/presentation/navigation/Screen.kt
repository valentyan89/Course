package com.example.courses.presentation.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth_screen")
    object Home : Screen("home_screen")
    object Favourite : Screen("favourite_screen")
}