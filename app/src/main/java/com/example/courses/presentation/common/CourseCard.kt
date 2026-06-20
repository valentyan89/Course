package com.example.courses.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.domain.model.Course

@Composable
fun CourseCard(
    course: Course,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color(0xFF4B6EEE))
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color(0x4D32333A), RoundedCornerShape(12.dp))
                        .padding(start = 8.dp, end = 6.dp, top = 4.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFF00C756),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = course.rate.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.4.sp
                    )
                }


                Box(
                    modifier = Modifier
                        .background(Color(0x4D32333A), RoundedCornerShape(12.dp))
                        .padding(start = 8.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = course.startDate,
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.4.sp
                    )
                }
            }


            IconButton(
                onClick = onLikeClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(36.dp)
                    .background(Color(0x80000000), CircleShape)
            ) {
                Icon(
                    imageVector = if (course.hasLike) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Favorite",
                    tint = if (course.hasLike) Color(0xFF00C756) else Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF24252A))
                .padding(16.dp)
        ) {
            Text(
                text = course.title,
                color = Color.White,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = course.text,
                color = Color(0xFFB9B9B9),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.4.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.price} Р",
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.15.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Подробнее",
                        color = Color(0xFF00C756),
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        letterSpacing = 0.4.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFF00C756),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}