package com.example.proyectofinalap2.data.remote.dto

data class MecanicoDto(
    val mecanicoId: Int,
    val nombres: String,
    val area: String,
    val telefono: String,
    val disponible: Int = 0,
)
