package com.example.proyectofinalap2.view.Solicitud


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyectofinalap2.data.remote.dto.SolicitudDto
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.SolicitudesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaSolicitudesScreen(navHostController: NavHostController, Id:Int, viewModel: SolicitudesViewModel = hiltViewModel()){


    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Solicitudes") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.SolicitarMecanico.route + "/${Id}")
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Nuevo")
            }
        },

        ){it
        val uiState by viewModel.uiState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            SolicitudListBody(navHostController = navHostController, uiState.Solicitud, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun SolicitudListBody(navHostController: NavHostController, solicitudList: List<SolicitudDto>, Onclick : (SolicitudDto) -> Unit,
                     viewModel: SolicitudesViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(solicitudList) { solicitudes ->
                SolicitudRow(navHostController = navHostController, solicitudes, viewModel)
            }
        }
    }
}

@Composable
fun SolicitudRow(navHostController: NavHostController, solicitud: SolicitudDto, viewModel: SolicitudesViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column() {
                Text(text = solicitud.concepto)
                Text(text = solicitud.fecha)
                Icon(
                    imageVector = when (solicitud.estado){
                        "Solicitada" -> {
                            Icons.Default.Star
                        }
                        else -> {
                            Icons.Default.TaskAlt
                        }
                    }, contentDescription = solicitud.estado
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {


                IconButton(
                    onClick = {
                        navHostController.navigate(Screen.editarSolicitud.route +"/${solicitud.solicitudId}/${solicitud.mecanicoId}")
                    }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                }

                IconButton(
                    onClick = {
                        viewModel.eliminar(solicitud.solicitudId)
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
    Divider(Modifier.fillMaxWidth())
}