package com.example.proyectofinalap2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.util.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(navHostController: NavHostController, Id:Int, viewModel: MecanicoViewModel = hiltViewModel()){

    remember {
        viewModel.setMecanico(Id)
        0
    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dashboard") })
        },

        ) {
        it
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp, vertical = 80.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().padding(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = viewModel.nombres,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = viewModel.area,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = viewModel.telefono,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                OutlinedButton(
                    onClick = { navHostController.navigate(Screen.ConsultaCitasScreen.route +"/${Id}") },
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
                    onClick = { navHostController.navigate(Screen.ConsultaSolicitudesScreen.route +"/${Id}") },
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
                    onClick = { navHostController.navigate(Screen.ConsultaReportesScreen.route +"/${Id}") },
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