package com.example.courses.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.courses.presentation.common.BottomNavItem
import com.example.courses.presentation.screens.FavoriteScreen
import com.example.courses.presentation.screens.HomeScreen
import com.example.courses.presentation.screens.LoginScreen

@Composable
fun CalendarNavHost() {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screen.Auth.route

    val bottomTabs = listOf(BottomNavItem.Home, BottomNavItem.Favourite, BottomNavItem.Account)
    val shouldShowBottomBar = currentRoute != Screen.Auth.route

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar(
                    containerColor = Color(0xFF1C1C1E),
                    modifier = Modifier.height(80.dp),
                    tonalElevation = 0.dp
                ) {
                    bottomTabs.forEach { item ->
                        val isSelected = currentRoute == item.name

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.name) {
                                    navController.navigate(item.name) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            label = {
                                Text(
                                    text = item.name,
                                    fontSize = 12.sp,
                                    color = if (isSelected) Color(0xFF00C756) else Color(0xFF8E8E93)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Auth.route,
            modifier = Modifier.padding(paddingValues)
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
                HomeScreen()
            }

            composable(Screen.Favourite.route) {
                FavoriteScreen()
            }
        }
    }
}