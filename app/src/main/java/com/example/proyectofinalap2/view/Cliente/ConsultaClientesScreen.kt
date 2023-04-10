package com.example.proyectofinalap2.view.Cliente

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
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.ClienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaClientesScreen(navHostController: NavHostController, viewModel: ClienteViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Clientes") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.registroNuevoClientes.route)
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
            ClienteListBody(navHostController = navHostController, uiState.Cliente, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun ClienteListBody(navHostController: NavHostController, clienteList: List<ClienteDto>, Onclick : (ClienteDto) -> Unit,
                     viewModel: ClienteViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(clienteList) { clientes ->
                ClienteRow(navHostController = navHostController, clientes, viewModel)
            }
        }
    }
}

@Composable
fun ClienteRow(navHostController: NavHostController, cliente: ClienteDto, viewModel: ClienteViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navHostController.navigate(Screen.editarClientes.route + "/${cliente.clienteId}") }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column() {
                Text(text = cliente.nombres)
                Text(text = cliente.direccion)
                Text(text = cliente.telefono)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = {
                        navHostController.navigate(Screen.editarClientes.route)
                    }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                }

                IconButton(
                    onClick = {
                        viewModel.eliminar(cliente.clienteId)
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
    Divider(Modifier.fillMaxWidth())
}