package com.example.proyectofinalap2.data.remote.dto

import java.util.Date

data class ReporteDto(
    val reporteId: Int,
    val concepto: Int,
    val fecha: Date,
    val clienteId: Int,
    val mecanicoId: Int,
)
