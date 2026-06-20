package com.example.courses.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courses.presentation.screens.FavoriteScreen
import com.example.courses.presentation.screens.HomeScreen
import com.example.courses.presentation.screens.LoginScreen

@Composable
fun CalendarNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route) {
            LoginScreen(
                onNavigateToMain = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Favourite.route) {
            FavoriteScreen(navController = navController)
        }
    }
}