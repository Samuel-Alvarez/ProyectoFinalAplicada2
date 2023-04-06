package com.example.proyectofinalap2.data.remote.dto

import java.util.Date

data class ReporteDto(
    val reporteId: Int,
    val concepto: String,
    val fecha: String,
    val clienteId: Int,
    val mecanicoId: Int,
)
