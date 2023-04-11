package com.example.proyectofinalap2.view.Vehiculo

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
fun registroNuevoVehiculo(navHostController: NavHostController, viewModel: VehiculosViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registrar Nuevo Vehiculo") },
            )
        },

        ){it

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, vertical = 60.dp)) {

            OutlinedTextField(
                value = viewModel.marca,
                onValueChange = { viewModel.marca = it },
                label = { Text(text = "Marca") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.BrandingWatermark,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.modelo,
                onValueChange = { viewModel.modelo = it},
                label = { Text(text = "Modelo") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ModelTraining,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.year,
                onValueChange = { viewModel.year = it },
                label = { Text(text = "Año") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CarRental,
                        contentDescription = null
                    )
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {
                    viewModel.guardar()
                    navHostController.navigate(Screen.ConsultaVehiculoScreen.route)

                }
            ) {
                Icon(imageVector = Icons.Filled.Save, contentDescription = "Save")
                Text(
                    text = "Guardar",
                    fontWeight = FontWeight.Black,
                )
            }

        }

    }
}