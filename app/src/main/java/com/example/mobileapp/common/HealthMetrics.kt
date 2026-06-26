package com.example.mobileapp.common

data class HealthMetrics(
    val heartRate: Int = 0,
    val steps: Int = 0,
    val calories: Double = 0.0,
    val distanceKm: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)
