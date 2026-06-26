package com.example.mobileapp.wear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.common.HealthMetrics

@Composable
fun WearScreen(metrics: HealthMetrics, alert: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "HealthWatch",
                color = Color.Cyan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            // Heart Rate
            Text(
                text = "${metrics.heartRate}",
                color = if (alert != null) Color.Red else Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(text = "BPM", color = Color.Gray, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Steps
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${metrics.steps}", color = Color.White, fontSize = 16.sp)
                    Text(text = "Pasos", color = Color.Gray, fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "%.1f".format(metrics.distanceKm), color = Color.White, fontSize = 16.sp)
                    Text(text = "Km", color = Color.Gray, fontSize = 10.sp)
                }
            }

            if (alert != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = alert,
                    color = Color.Red,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape).padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
