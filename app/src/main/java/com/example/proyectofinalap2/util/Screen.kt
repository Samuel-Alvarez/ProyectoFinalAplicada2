package com.example.proyectofinalap2.util

sealed class Screen(val route: String) {
    object RegistroProblema: Screen("RegistroProblema")
    object SolicitarMecanico: Screen("SolicitarMecanico")
    object CitaTaller: Screen("CitaTaller")
    object mecanicoListado: Screen("mecanicoListado")
    object registroMecanicoNuevo: Screen("registroMecanicoNuevo")
    object DashBoard: Screen("DashBoard")
    object ConsultaCitasScreen: Screen("ConsultaCitasScreen")
    object ConsultasSolicitudesScreen: Screen("ConsultasSolicitudesScreen")
    object ConsultasReportesScreen: Screen("ConsultasReportesScreen")


}