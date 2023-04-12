package com.example.proyectofinalap2.data.remote.dto

import java.util.*

class SolicitudDto(
    val solicitudId: Int,
    val concepto: String,
    val fecha: String,
    val estado: String,
    val clienteId: Int,
    val mecanicoId: Int,
)
