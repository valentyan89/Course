package com.example.courses.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.courses.presentation.navigation.Screen

@Composable
fun CoursesBottomBar(navController: NavController) {
    val bottomTabs = listOf(BottomNavItem.Home, BottomNavItem.Favourite, BottomNavItem.Account)

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Column(
        modifier = Modifier
            .background(Color(0xFF24252A))
            .navigationBarsPadding()
    ) {
        HorizontalDivider(
            thickness = 1.5.dp,
            color = Color(0xFF4D535E)
        )

        NavigationBar(
            containerColor = Color(0xFF24252A),
            modifier = Modifier.height(80.dp),
            tonalElevation = 0.dp,
            windowInsets = NavigationBarDefaults.windowInsets
        ) {
            bottomTabs.forEach { item ->
                val targetRoute = when (item) {
                    BottomNavItem.Home -> Screen.Home.route
                    BottomNavItem.Favourite -> Screen.Favourite.route
                    else -> Screen.Auth.route
                }

                val isSelected = currentRoute == targetRoute

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentRoute != targetRoute) {
                            navController.navigate(targetRoute) {
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
                            color = if (isSelected) Color(0xFF00C756) else Color(0xFFF2F2F3),
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}