package com.example.courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _openBrowserEvent = MutableSharedFlow<String>()
    val openBrowserEvent = _openBrowserEvent.asSharedFlow()

    private val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    val isLoginButtonEnabled: StateFlow<Boolean> = combine(_email, _password) { currentEmail, currentPassword ->
        currentEmail.matches(emailRegex) && currentPassword.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun onEmailChanged(input: String) {
        val hasKyrillic = input.any { it in '\u0400'..'\u04FF' }
        if (!hasKyrillic) _email.value = input
    }


    fun onPasswordChanged(input: String) {
        _password.value = input
    }

    fun onVkClicked() {
        viewModelScope.launch {
            _openBrowserEvent.emit("https://vk.com/")
        }
    }

    fun onOkClicked() {
        viewModelScope.launch {
            _openBrowserEvent.emit("https://ok.ru/")
        }
    }
}