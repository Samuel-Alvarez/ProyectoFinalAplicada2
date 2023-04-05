package com.example.proyectofinalap2.view.Solicitud

import com.example.proyectofinalap2.data.remote.dto.MecanicoDto

data class SolicitudListState(
    val isLoading: Boolean = false,
    val Solicitud: List<MecanicoDto> = emptyList(),
    val error: String = ""
)