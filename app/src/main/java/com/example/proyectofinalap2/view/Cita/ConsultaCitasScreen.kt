package com.example.proyectofinalap2.view.Cita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.data.remote.dto.CitaDto
import com.example.proyectofinalap2.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaCitasScreen(navHostController: NavHostController, Id:Int, viewModel: CitasViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Citas") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.CitaTaller.route + "/${Id}")
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
            CitaListBody(navHostController = navHostController, uiState.Cita, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun CitaListBody(navHostController: NavHostController, citaList: List<CitaDto>, Onclick : (CitaDto) -> Unit,
                 viewModel: CitasViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(citaList) { citas ->
                CitaRow(navHostController = navHostController, citas, viewModel)
            }
        }
    }
}

@Composable
fun CitaRow(navHostController: NavHostController, cita: CitaDto, viewModel: CitasViewModel = hiltViewModel()) {
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
                Text(text = cita.concepto)
                Text(text = cita.fecha)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = {
                        navHostController.navigate(Screen.editarCita.route + "/${cita.citaId}/${cita.mecanicoId}")
                    }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                }

                IconButton(
                    onClick = {
                        viewModel.eliminar(cita.citaId)
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
    Divider(Modifier.fillMaxWidth())
}
