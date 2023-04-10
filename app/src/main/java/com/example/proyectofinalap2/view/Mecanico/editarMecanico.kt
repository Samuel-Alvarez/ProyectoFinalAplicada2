package com.example.proyectofinalap2.view.Mecanico

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
import com.example.proyectofinalap2.view.MecanicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editarMecanico(navHostController: NavHostController, Id:Int, viewModel: MecanicoViewModel = hiltViewModel()){

    remember {
        viewModel.setMecanico(Id)
        0
    }

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Editar Mecanico") },
            )
        },

        ){it

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, vertical = 60.dp)) {

            OutlinedTextField(
                value = viewModel.nombres,
                onValueChange = { viewModel.nombres = it },
                label = { Text(text = "Nombres") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.area,
                onValueChange = { viewModel.area = it },
                label = { Text(text = "Area") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AreaChart,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.telefono,
                onValueChange = { viewModel.telefono = it },
                label = { Text(text = "Telefono") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PhoneAndroid,
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
                    navHostController.navigate(Screen.mecanicoListado.route)
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