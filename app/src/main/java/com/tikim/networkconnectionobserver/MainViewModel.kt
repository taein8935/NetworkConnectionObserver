package com.tikim.networkconnectionobserver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val connectivityObserver: ConnectivityObserver
): ViewModel() {
    val isConnected = connectivityObserver
        .isConnected
        .stateIn(
            scope = viewModelScope,
            started =  SharingStarted.WhileSubscribed(5_000L),
            initialValue = false
        )
}