package com.example.proyectofinalap2.view.Reporte

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyectofinalap2.data.remote.dto.ReporteDto
import com.example.proyectofinalap2.util.Screen



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaReportesScreen(navHostController: NavHostController, Id: Int, viewModel: ReportesViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Reportes") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.RegistroProblema.route + "/${Id}")
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
            ReporteListBody(navHostController = navHostController, uiState.Reporte, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun ReporteListBody(navHostController: NavHostController, reporteList: List<ReporteDto>, Onclick : (ReporteDto) -> Unit,
                     viewModel: ReportesViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(reporteList) { reportes ->
                ReporteRow(navHostController = navHostController, reportes, viewModel)
            }
        }
    }
}

@Composable
fun ReporteRow(navHostController: NavHostController, reporte: ReporteDto, viewModel: ReportesViewModel = hiltViewModel()) {
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
                Text(text = reporte.concepto)
                Text(text = reporte.fecha)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = {

                    }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                }

                IconButton(
                    onClick = {
                        viewModel.eliminar(reporte.reporteId)
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
    Divider(Modifier.fillMaxWidth())
}