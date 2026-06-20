package com.example.courses.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.courses.presentation.common.CourseCard
import com.example.courses.presentation.viewmodel.CoursesViewModel

@Composable
fun FavoriteScreen(
    viewModel: CoursesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val favoriteCourses by viewModel.favoriteCoursesState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))


        Text(
            text = "Избранное",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),

            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(favoriteCourses, key = { it.id }) { course ->
                CourseCard(
                    course = course,
                    onLikeClick = { viewModel.toggleLike(course) }
                )
            }
        }
    }
}