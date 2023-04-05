package com.example.proyectofinalap2.util

sealed class Screen(val route: String) {
    object RegistroProblema: Screen("RegistroProblema")
    object SolicitarMecanico: Screen("SolicitarMecanico")
    object CitaTaller: Screen("CitaTaller")
    object mecanicoListado: Screen("mecanicoListado")
}