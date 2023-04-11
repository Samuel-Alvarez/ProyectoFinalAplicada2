package com.example.proyectofinalap2.view.Vehiculo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.util.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editarVehiculos(navHostController: NavHostController, Id:Int, viewModel: VehiculosViewModel = hiltViewModel()){

    remember {
        viewModel.setVehiculo(Id)
        0
    }

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Editar Vehiculos") },
            )
        },

        ){it

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, vertical = 60.dp)) {

            OutlinedTextField(
                value = viewModel.marca,
                onValueChange = {viewModel.marca = it },
                label = { Text(text = "Marca") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PhoneAndroid,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.modelo,
                onValueChange = { viewModel.modelo = it },
                label = { Text(text = "Modelo") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
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
                        imageVector = Icons.Default.Directions,
                        contentDescription = null
                    )
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {
                    viewModel.modificar()
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