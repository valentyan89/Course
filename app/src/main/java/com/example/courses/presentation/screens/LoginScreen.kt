package com.example.courses.presentation.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.courses.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToMain: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val isButtonEnabled by viewModel.isLoginButtonEnabled.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.openBrowserEvent.collect { url ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF151515))
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "Вход",
            color = Color.White,
            fontSize = 28.sp,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Email",
            color = Color(0xFFF2F2F3),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.15.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = email,
            onValueChange = { viewModel.onEmailChanged(it) },
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            singleLine = true,
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFF32333A), CircleShape)
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (email.isEmpty()) {
                        Text(
                            text = "example@gmail.com",
                            color = Color(0xFF6E6E72),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Пароль",
            color = Color(0xFFF2F2F3),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.15.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFF32333A), CircleShape)
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (password.isEmpty()) {
                        Text(
                            text = "Введите пароль",
                            color = Color(0xFF6E6E72),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavigateToMain() },
            enabled = isButtonEnabled,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00C756),
                disabledContainerColor = Color(0xFF00C756)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Вход",
                color = Color(0xFFF2F2F3),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "Нет аккаунта? ",
                    color = Color(0xFFF2F2F3),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.4.sp
                )

                Text(
                    text = "Регистрация",
                    color = Color(0xFF12B956),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.4.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Забыл пароль",
                color = Color(0xFF12B956),
                fontSize = 12.sp,
                lineHeight = 15.sp,
                letterSpacing = 0.4.sp
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        HorizontalDivider(color = Color(0xFF2C2C2E), thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.onVkClicked() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2D81E0)),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            ) {
                Text(text = "ВК", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.onOkClicked() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            ) {
                Text(text = "ОК", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}