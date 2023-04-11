package com.example.proyectofinalap2.view.Vehiculo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.data.remote.dto.ClienteDto
import com.example.proyectofinalap2.data.remote.dto.VehiculoDto
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.ClienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaVehiculoScreen(navHostController: NavHostController, viewModel: VehiculosViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Vehiculos") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.registroNuevoVehiculos.route)
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
            VehiculoListBody(navHostController = navHostController, uiState.Vehiculo, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun VehiculoListBody(navHostController: NavHostController, vehiculoList: List<VehiculoDto>, Onclick : (VehiculoDto) -> Unit,
                     viewModel: VehiculosViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(vehiculoList) { vehiculos ->
                VehiculoRow(navHostController = navHostController, vehiculos, viewModel)
            }
        }
    }
}

@Composable
fun VehiculoRow(navHostController: NavHostController, vehiculo: VehiculoDto, viewModel: VehiculosViewModel = hiltViewModel()) {
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
                Text(text = vehiculo.vehiculoId.toString())
                Text(text = vehiculo.marca)
                Text(text = vehiculo.modelo)
                Text(text = vehiculo.year)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = {
                        navHostController.navigate(Screen.editarVehiculo.route +"/${vehiculo.vehiculoId}")
                    }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "delete")
                }


                IconButton(
                    onClick = {
                        viewModel.eliminar(vehiculo.vehiculoId)
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
    Divider(Modifier.fillMaxWidth())
}