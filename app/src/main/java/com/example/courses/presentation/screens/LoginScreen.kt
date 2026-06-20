package com.example.courses.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Вход",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Email", color = Color(0xFF8E8E93), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { viewModel.onEmailChanged(it) },
            placeholder = { Text("example@gmail.com", color = Color(0xFF48484A)) },
            shape = CircleShape,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2C2C2E),
                unfocusedContainerColor = Color(0xFF2C2C2E),
                disabledContainerColor = Color(0xFF2C2C2E),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Пароль", color = Color(0xFF8E8E93), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            placeholder = { Text("Введите пароль", color = Color(0xFF48484A)) },
            shape = CircleShape,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2C2C2E),
                unfocusedContainerColor = Color(0xFF2C2C2E),
                disabledContainerColor = Color(0xFF2C2C2E),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavigateToMain() },
            enabled = isButtonEnabled,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00C756),
                disabledContainerColor = Color(0xFF2C2C2E)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(
                text = "Вход",
                color = if (isButtonEnabled) Color.White else Color(0xFF48484A),
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
                Text(text = "Нет аккаунта? ", color = Color.White, fontSize = 14.sp)
                Text(text = "Регистрация", color = Color(0xFF00C756), fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Забыл пароль", color = Color(0xFF00C756), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(color = Color(0xFF2C2C2E), thickness = 1.dp)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.onVkClicked() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2D81E0)),
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp)
            ) {
                Text(text = "ВК", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.onOkClicked() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)),
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp)
            ) {
                Text(text = "ОК", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}