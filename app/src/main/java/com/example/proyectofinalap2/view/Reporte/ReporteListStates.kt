package com.example.proyectofinalap2.view.Reporte

import com.example.proyectofinalap2.data.remote.dto.ReporteDto

data class ReporteListStates(
    val isLoading: Boolean = false,
    val Reporte: List<ReporteDto> = emptyList(),
    val error: String = ""
)