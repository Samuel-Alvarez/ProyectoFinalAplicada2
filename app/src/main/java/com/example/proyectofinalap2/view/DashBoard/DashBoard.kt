package com.example.proyectofinalap2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(navHostController: NavHostController){

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dashboard") })
        },

    ) {
        it
        Column(

            modifier = Modifier
                .fillMaxWidth().fillMaxHeight()
                .padding(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 5.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFFF04444)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = "$",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = "$",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFFC282DA)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = "$",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = "$",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }


            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                OutlinedButton(
                    onClick = { navHostController.navigate("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),

                    ) {
                    Text(
                        text = "Cita al taller",
                        color = Color.Black
                    )
                }
                OutlinedButton(
                    onClick = { navHostController.navigate("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),

                    ) {
                    Text(
                        text = "Solicitar Mecanico",
                        color = Color.Black
                    )
                }
                OutlinedButton(
                    onClick = { navHostController.navigate("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                ) {
                    Text(
                        text = "Reportar Problema",
                        color = Color.Black
                    )
                }
            }
        }
    }
}