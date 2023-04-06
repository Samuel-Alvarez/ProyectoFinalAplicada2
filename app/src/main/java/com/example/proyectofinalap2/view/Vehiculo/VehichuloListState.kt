package com.example.proyectofinalap2.view.Vehiculo

import com.example.proyectofinalap2.data.remote.dto.VehiculoDto


data class VehichuloListState(
    val isLoading: Boolean = false,
    val Vehiculo: List<VehiculoDto> = emptyList(),
    val error: String = ""
)