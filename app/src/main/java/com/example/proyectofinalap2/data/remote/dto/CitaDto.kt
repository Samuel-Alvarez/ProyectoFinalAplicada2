package com.example.proyectofinalap2.data.remote.dto

import java.util.Date

data class CitaDto(
    val citaId: Int,
    val concepto: String,
    val fecha: String,
    val estado: String,
    val clienteId: Int,
    val mecanicoId: Int,
)
