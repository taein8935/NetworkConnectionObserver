package com.tikim.networkconnectionobserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tikim.networkconnectionobserver.ui.theme.NetworkConnectionObserverTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NetworkConnectionObserverTheme {

                // https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories?hl=ko#:~:text=%EB%98%90%EB%8A%94%20ViewModel%20%ED%8C%A9%ED%86%A0%EB%A6%AC,%7D%0A%20%20%20%20%7D%0A%7D
                val viewModel: MainViewModel by viewModels {
                    viewModelFactory {
                        initializer {
                            MainViewModel(
                                connectivityObserver = NetworkConnectivityObserver(
                                    context = this@MainActivity
                                )
                            )
                        }
                    }
                }

                val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "isConnected: $isConnected",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

