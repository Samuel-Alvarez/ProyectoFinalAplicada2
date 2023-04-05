package com.example.proyectofinalap2.view

import com.example.proyectofinalap2.data.remote.dto.MecanicoDto

data class MecanicoListState(
    val isLoading: Boolean = false,
    val Mecanico: List<MecanicoDto> = emptyList(),
    val error: String = ""
)
