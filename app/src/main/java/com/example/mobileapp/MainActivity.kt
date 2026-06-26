package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileapp.common.HealthMetrics
import com.example.mobileapp.mobile.CompanionDashboard
import com.example.mobileapp.wear.HealthSensorManager
import com.example.mobileapp.wear.WearScreen
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val sensorManager = HealthSensorManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(sensorManager)
                }
            }
        }
    }
}

@Composable
fun MainNavigation(sensorManager: HealthSensorManager) {
    var currentView by remember { mutableStateOf("Wearable") }
    var metrics by remember { mutableStateOf(HealthMetrics()) }
    var history by remember { mutableStateOf(listOf<HealthMetrics>()) }
    var alert by remember { mutableStateOf<String?>(null) }

    // Simulate sensor data
    LaunchedEffect(Unit) {
        sensorManager.getMetricsFlow().collectLatest { newMetrics ->
            metrics = newMetrics
            history = (history + newMetrics).takeLast(20)
            alert = sensorManager.checkAlerts(newMetrics)
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentView == "Wearable",
                    onClick = { currentView = "Wearable" },
                    icon = { Icon(Icons.Default.Watch, contentDescription = "Wearable") },
                    label = { Text("Reloj") }
                )
                NavigationBarItem(
                    selected = currentView == "Mobile",
                    onClick = { currentView = "Mobile" },
                    icon = { Icon(Icons.Default.Smartphone, contentDescription = "Mobile") },
                    label = { Text("Celular") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentView) {
                "Wearable" -> WearScreen(metrics, alert)
                "Mobile" -> CompanionDashboard(history)
            }
        }
    }
}
