package com.example.proyectofinalap2.view.Cita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaCitasScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Listado de Citas") },

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate("")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },

    ) {it

        Column(
            modifier = Modifier.padding(8.dp)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                /*items() {

                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }*/
            }
        }
    }
//    Row(modifier = Modifier.fillMaxWidth()){
//        Text(
//            text = cita.concepto,
//        )
//
//        Text(
//            text = "",
//            textAlign = TextAlign.End,
//            modifier = Modifier.weight(2f)
//        )
//
//        Icon(
//            imageVector = when (cita.estatus) {
//                "Solicitado" -> {
//                    Icons.Default.Star
//                }
//                "En espera" -> {
//                    Icons.Default.Update
//                }
//                else -> {
//                    Icons.Default.TaskAlt
//
//                }
//            }, contentDescription = cita.estatus,
//        )
//
//        IconButton(
//            onClick = {
//                CitasViewModel.eliminar(Int)
//            },
//            modifier = Modifier.align(alignment = Alignment.Bottom )
//        ) {
//            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
//        }
//    }
}
