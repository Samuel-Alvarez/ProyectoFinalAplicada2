package com.example.proyectofinalap2.util

sealed class Screen(val route: String) {
    object RegistroProblema: Screen("RegistroProblema")
    object SolicitarMecanito: Screen("SolicitarMecanito")
    object CitaTaller: Screen("CitaTaller")
}