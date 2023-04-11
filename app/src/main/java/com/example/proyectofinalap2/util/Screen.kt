package com.example.proyectofinalap2.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.List
import androidx.compose.material.icons.twotone.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object RegistroProblema: Screen("RegistroProblema", "Reportar Problema", Icons.TwoTone.List)
    object SolicitarMecanico: Screen("SolicitarMecanico", "Solicitar Mecanico", Icons.TwoTone.List)
    object CitaTaller: Screen("CitaTaller", "Cita al Taller", Icons.TwoTone.List)
    object mecanicoListado: Screen("mecanicoListado", "Listado de Mecanicos", Icons.TwoTone.List)
    object PantallaMecanico: Screen("PantallaMecanico", "HomeScreen", Icons.TwoTone.List)
    object registroMecanicoNuevo: Screen("registroMecanicoNuevo", "Nuevo Mecanico", Icons.TwoTone.List)
    object DashBoard: Screen("DashBoard", "DashBoard", Icons.TwoTone.List)
    object ConsultaCitasScreen: Screen("ConsultaCitasScreen", "Citas", Icons.TwoTone.List)
    object ConsultaSolicitudesScreen: Screen("ConsultaSolicitudesScreen", "Solicitudes", Icons.TwoTone.List)
    object ConsultaReportesScreen: Screen("ConsultaReportesScreen", " Listado de Reportes", Icons.TwoTone.List)
    object registroNuevoClientes: Screen("registroNuevoClientes", " Nuevo Cliente", Icons.TwoTone.List)
    object registroNuevoVehiculos: Screen("registroNuevoVehiculos", "Nuevo Vehiculo", Icons.TwoTone.List)
    object ConsultaClientesScreen: Screen("ConsultaClientesScreen", "Clientes", Icons.TwoTone.Person)
    object editarClientes: Screen("editarClientes", "Editar Clientes", Icons.TwoTone.List)
    object ConsultaVehiculoScreen: Screen("ConsultaVehiculoScreen", "Vehiculos", Icons.TwoTone.List)
    object editarMecanico: Screen("editarMrcanico", "Editar Clientes", Icons.TwoTone.List)
    object editarCita: Screen("editarCita", "Editar Citas", Icons.TwoTone.List)
    object editarReporte: Screen("editarReporte", "Editar Reporte", Icons.TwoTone.List)
    object editarSolicitud: Screen("editarSolicitud", "Editar Solicitud", Icons.TwoTone.List)
    object editarVehiculo: Screen("editarVehiculo", "Editar Vehiculo", Icons.TwoTone.List)


}