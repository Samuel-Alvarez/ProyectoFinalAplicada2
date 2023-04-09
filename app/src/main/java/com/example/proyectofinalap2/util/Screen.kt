package com.example.proyectofinalap2.util

sealed class Screen(val route: String) {
    object RegistroProblema: Screen("RegistroProblema")
    object SolicitarMecanico: Screen("SolicitarMecanico")
    object CitaTaller: Screen("CitaTaller")
    object mecanicoListado: Screen("mecanicoListado")
    object PantallaMecanico: Screen("PantallaMecanico")
    object registroMecanicoNuevo: Screen("registroMecanicoNuevo")
    object DashBoard: Screen("DashBoard")
    object ConsultaCitasScreen: Screen("ConsultaCitasScreen")
    object ConsultaSolicitudesScreen: Screen("ConsultaSolicitudesScreen")
    object ConsultaReportesScreen: Screen("ConsultaReportesScreen")


}