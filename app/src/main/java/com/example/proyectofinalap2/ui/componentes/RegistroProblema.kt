package com.example.proyectofinalap2.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.MecanicoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroProblema(navHostController: NavHostController, viewModel: MecanicoViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Reportar Problema") })
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
                    value = viewModel.nombres,
                    onValueChange = {viewModel.nombres = it },
                    label = { Text(text = "Nombres Mecanico") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null
                        )
                    }
                )

                OutlinedTextField(
                    value = viewModel.area,
                    onValueChange = { viewModel.area = it},
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
                    onValueChange = {},
                    label = { Text(text = "Estado") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(
                    value = viewModel.nombres,
                    onValueChange = { viewModel.nombres = it},
                    label = { Text(text = "Nombres Cliente") },
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
                    label = { Text(text = "Marca vehiculo") },
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
                    label = { Text(text = "Año vehiculo") },
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
                        viewModel.guardar()
                        navHostController.navigate(Screen.mecanicoListado.route)

                    }
                ) {
                    Icon(imageVector = Icons.Filled.Save, contentDescription = "Send")
                    Text(
                        text = "Enviar",
                        fontWeight = FontWeight.Black,
                    )
                }
            }
        }
    }
}
