package com.example.mobileapp.wear

import com.example.mobileapp.common.HealthMetrics
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class HealthSensorManager {

    fun getMetricsFlow(): Flow<HealthMetrics> = flow {
        var steps = 0
        while (true) {
            val heartRate = Random.nextInt(60, 120)
            steps += Random.nextInt(0, 5)
            val calories = steps * 0.04
            val distance = steps * 0.0008

            emit(HealthMetrics(
                heartRate = heartRate,
                steps = steps,
                calories = calories,
                distanceKm = distance
            ))
            delay(2000) // Emit every 2 seconds
        }
    }

    fun checkAlerts(metrics: HealthMetrics): String? {
        return if (metrics.heartRate > 100) {
            "¡Alerta! Frecuencia cardiaca elevada: ${metrics.heartRate} BPM"
        } else if (metrics.heartRate < 50) {
            "¡Alerta! Frecuencia cardiaca baja: ${metrics.heartRate} BPM"
        } else {
            null
        }
    }
}
