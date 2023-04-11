package com.example.proyectofinalap2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinalap2.ui.componentes.*
import com.example.proyectofinalap2.ui.theme.ProyectoFinalAp2Theme
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.Cita.ConsultaCitasScreen
import com.example.proyectofinalap2.view.Cita.editarCita
import com.example.proyectofinalap2.view.Cliente.ConsultaClientesScreen
import com.example.proyectofinalap2.view.Cliente.editarClientes
import com.example.proyectofinalap2.view.Cliente.registroNuevoClientes
import com.example.proyectofinalap2.view.DashBoard
import com.example.proyectofinalap2.view.Mecanico.editarMecanico
import com.example.proyectofinalap2.view.Mecanico.registroMecanicoNuevo
import com.example.proyectofinalap2.view.Reporte.ConsultaReportesScreen
import com.example.proyectofinalap2.view.Reporte.editarReporte
import com.example.proyectofinalap2.view.Solicitud.ConsultaSolicitudesScreen
import com.example.proyectofinalap2.view.Solicitud.editarSolicitud
import com.example.proyectofinalap2.view.Vehiculo.ConsultaVehiculoScreen
import com.example.proyectofinalap2.view.Vehiculo.editarVehiculos
import com.example.proyectofinalap2.view.Vehiculo.registroNuevoVehiculo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalAp2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myApp()
                }
            }
        }
    }
}

@Composable
fun myApp(){
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.PantallaMecanico.route) {

        composable(Screen.PantallaMecanico.route) {
            PantallaMecanico(navHostController = navHostController)
        }

        composable(Screen.mecanicoListado.route) {
            DrawerMenu(navHostController = navHostController)
            //mecanicoListado(navHostController = navHostController)
        }

        composable(Screen.registroMecanicoNuevo.route){
            registroMecanicoNuevo(navHostController = navHostController)
        }

        composable(Screen.DashBoard.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            DashBoard(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.ConsultaCitasScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            ConsultaCitasScreen(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.ConsultaSolicitudesScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            ConsultaSolicitudesScreen(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.ConsultaReportesScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            ConsultaReportesScreen(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.CitaTaller.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            CitaTaller(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.RegistroProblema.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            RegistroProblema(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.SolicitarMecanico.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            SolicitarMecanico(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.editarMecanico.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            editarMecanico(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.editarCita.route + "/{id}/{mecanicoId}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            },
                navArgument(name = "mecanicoId"){
                    type = NavType.IntType
                }
            )
        ){
            editarCita(navHostController = navHostController,
                Id = it.arguments?.getInt("id")?: 0,
                mecanicoId = it.arguments?.getInt("mecanicoId")?: 0
            )
        }

        composable(Screen.editarSolicitud.route + "/{id}/{mecanicoId}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            },
                navArgument(name = "mecanicoId"){
                    type = NavType.IntType
                }
            )
        ){
            editarSolicitud(navHostController = navHostController,
                Id = it.arguments?.getInt("id")?: 0,
                mecanicoId = it.arguments?.getInt("mecanicoId")?: 0
            )
        }

        composable(Screen.editarReporte.route + "/{id}/{mecanicoId}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            },
                navArgument(name = "mecanicoId"){
                    type = NavType.IntType
                }
            )
        ){
            editarReporte(navHostController = navHostController,
                Id = it.arguments?.getInt("id")?: 0,
                mecanicoId = it.arguments?.getInt("mecanicoId")?: 0
            )
        }

        //Clientes y Vehiculos
        composable(Screen.ConsultaClientesScreen.route){
            ConsultaClientesScreen(navHostController = navHostController)
        }

        composable(Screen.ConsultaVehiculoScreen.route){
            ConsultaVehiculoScreen(navHostController = navHostController)
        }

        composable(Screen.registroNuevoClientes.route){
            registroNuevoClientes(navHostController = navHostController)
        }

        composable(Screen.registroNuevoVehiculos.route){
            registroNuevoVehiculo(navHostController = navHostController)
        }

        composable(Screen.editarVehiculo.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            editarVehiculos(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.editarClientes.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            editarClientes(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    navHostController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val ic  = Icons.TwoTone.Favorite

    val items = listOf(Screen.ConsultaClientesScreen, Screen.ConsultaVehiculoScreen,)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            navHostController.navigate(item.route)
                        },

                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                mecanicoListado(navHostController = navHostController)
                Button(onClick = { scope.launch { drawerState.open() } }) {

                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ProyectoFinalAp2Theme(){
        myApp()
    }
}