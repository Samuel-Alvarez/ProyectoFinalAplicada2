package com.example.proyectofinalap2.view

import com.example.proyectofinalap2.data.remote.dto.ClienteDto

data class ClienteListState(
    val isLoading: Boolean = false,
    val Cliente: List<ClienteDto> = emptyList(),
    val error: String = ""
)