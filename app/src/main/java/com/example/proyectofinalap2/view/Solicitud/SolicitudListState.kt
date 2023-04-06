package com.example.proyectofinalap2.view.Solicitud

import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import com.example.proyectofinalap2.data.remote.dto.SolicitudDto

data class SolicitudListState(
    val isLoading: Boolean = false,
    val Solicitud: List<SolicitudDto> = emptyList(),
    val error: String = ""
)