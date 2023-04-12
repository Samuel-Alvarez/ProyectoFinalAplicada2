package com.example.proyectofinalap2.view.Solicitud

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.SolicitudesViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editarSolicitud(navHostController: NavHostController, Id:Int, mecanicoId: Int, viewModel: SolicitudesViewModel = hiltViewModel()) {
    remember {
        viewModel.mecanicoId = mecanicoId
        viewModel.setSolicitud(Id)
        0
    }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val contexto = LocalContext.current


    val date = DatePickerDialog(
        contexto, { d, year, month, day ->
            val month = month + 1
            viewModel.fecha = "$year-$month-$day"
        }, year, month, day
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Editar Solicitud") })
        },

        ) {
        it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, vertical = 60.dp),
            ) {


                OutlinedTextField(
                    value = viewModel.fecha,
                    onValueChange = { viewModel.fecha = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Fecha") },
                    readOnly = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "",
                        )
                    },

                    trailingIcon = {
                        IconButton(
                            onClick = { date.show() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = "",
                            )
                        }
                    }

                )
                OutlinedTextField(
                    value = viewModel.concepto,
                    onValueChange = { viewModel.concepto = it },
                    label = { Text(text = "Concepto Problema") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.expanded = true },
                    value = viewModel.estado,
                    enabled = false, readOnly = true,
                    label = { Text(text = "Estado") },
                    onValueChange = { viewModel.estado = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.QueryStats,
                            contentDescription = null
                        )
                    }
                )

                DropdownMenu(
                    expanded = viewModel.expanded,
                    onDismissRequest = { viewModel.expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    viewModel.solicitudesEstado.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(text = opcion, textAlign = TextAlign.Center)
                            },
                            onClick = {
                                viewModel.expanded = false
                                viewModel.estado = opcion
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
                OutlinedTextField(
                    value = viewModel.mecanicoId.toString(),
                    onValueChange = { viewModel.mecanicoId },
                    label = { Text(text = "Id Mecanico") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = viewModel.clienteId,
                    onValueChange = { viewModel.clienteId = it },
                    label = { Text(text = "Id Cliente") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = {
                        viewModel.modificar()
                        navHostController.navigate(Screen.ConsultaSolicitudesScreen.route + "/${Id}")
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Save, contentDescription = "Send")
                    Text(
                        text = "Solicitar",
                        fontWeight = FontWeight.Black,
                    )
                }
            }
        }
    }
}