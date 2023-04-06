package com.example.proyectofinalap2.view.Cita

import com.example.proyectofinalap2.data.remote.dto.CitaDto

data class CitaListState(
    val isLoading: Boolean = false,
    val Cita: List<CitaDto> = emptyList(),
    val error: String = ""
)