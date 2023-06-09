package com.example.proyectofinalap2.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalap2.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.proyectofinalap2.util.Screen


@Composable
fun PantallaMecanico(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Logo de mi aplicación",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight().padding(bottom = 175.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IniciarButton(onClick = { navHostController.navigate(Screen.mecanicoListado.route)}
            )
        }
    }
}

@Composable
fun IniciarButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(top = 16.dp),
        shape = MaterialTheme.shapes.large,
        colors = buttonColors(Color(0xFB6C1C81))
    ) {
        Text(text = "Iniciar", color = Color.White)
    }
}