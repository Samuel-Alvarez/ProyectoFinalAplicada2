package com.example.proyectofinalap2.view.Vehiculo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registroNuevoVehiculo(){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registrar Nuevo Vehiculo") },
            )
        },

        ){it

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, vertical = 60.dp)) {

            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text(text = "modelo") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text(text = "marca") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AreaChart,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text(text = "year") },
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