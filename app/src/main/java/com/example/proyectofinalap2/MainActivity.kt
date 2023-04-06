package com.example.proyectofinalap2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinalap2.ui.componentes.RegistroProblema
import com.example.proyectofinalap2.ui.componentes.SolicitarMecanico
import com.example.proyectofinalap2.ui.componentes.mecanicoListado
import com.example.proyectofinalap2.ui.theme.ProyectoFinalAp2Theme
import com.example.proyectofinalap2.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalAp2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myApp()
                }
            }
        }
    }
}

@Composable
fun myApp(){
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.mecanicoListado.route) {

        composable(Screen.mecanicoListado.route) {
            mecanicoListado(navHostController = navHostController)
        }

        composable(Screen.RegistroProblema.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            Log.d("Args", it.arguments?.getInt("id").toString())
            //RegistroProblema(navHostController = navHostController, Id = it.arguments?.getInt("id")?: 0)
        }

        composable(Screen.SolicitarMecanico.route){
            SolicitarMecanico(navHostController = navHostController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ProyectoFinalAp2Theme(){
        myApp()
    }
}