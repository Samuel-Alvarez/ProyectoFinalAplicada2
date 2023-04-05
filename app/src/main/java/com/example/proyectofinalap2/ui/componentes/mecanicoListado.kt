package com.example.proyectofinalap2.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import com.example.proyectofinalap2.util.Screen
import com.example.proyectofinalap2.view.MecanicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mecanicoListado(navHostController: NavHostController, viewModel: MecanicoViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Mecanicos") })
        },

        ){it
        val uiState by viewModel.uiState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            MecanicoListBody(navHostController = navHostController, uiState.Mecanico, Onclick = {}, viewModel)
        }

    }
}

@Composable
fun MecanicoListBody(navHostController: NavHostController, mecanicoList: List<MecanicoDto>, Onclick : (MecanicoDto) -> Unit,
                   viewModel: MecanicoViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(mecanicoList) { mecanicos ->
                MecanicoRow(navHostController = navHostController, mecanicos, viewModel)
            }
        }
    }
}

@Composable
fun MecanicoRow(navHostController: NavHostController, mecanico: MecanicoDto, viewModel: MecanicoViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navHostController.navigate(Screen.RegistroProblema.route + "/${mecanico.mecanicoId}") }
    ) {

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {

            Column(

            ) {

                Row(

                ) {
                    Text(
                        text = mecanico.nombres,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(3f)
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = mecanico.area,
                    )

                    Text(
                        text = "",
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(2f)
                    )

                    ///
                }

            }
        }

    }
    Divider(Modifier.fillMaxWidth())
}