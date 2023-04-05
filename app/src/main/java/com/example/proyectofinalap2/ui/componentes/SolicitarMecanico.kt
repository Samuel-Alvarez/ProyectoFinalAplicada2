package com.example.proyectofinalap2.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.view.SolicitudesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitarMecanico(navHostController: NavHostController, viewModel: SolicitudesViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Solicitar Mecanico") })
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
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Nombre Mecanico") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null
                        )
                    }
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Area") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Nombre Cliente") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Direccion") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Fecha") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
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
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Marca vehículo") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Año vehículo") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = {

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