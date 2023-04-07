package com.example.proyectofinalap2.view.Cita

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.CitaDto
import com.example.proyectofinalap2.data.repository.CitasRepository
import com.example.proyectofinalap2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CitaState(
    val isLoading: Boolean = false,
    val cita: CitaDto? =  null,
    val error: String = ""
)

@HiltViewModel
class CitasViewModel @Inject constructor(
    private val citaRepository: CitasRepository
): ViewModel() {

    val citasEstatus = listOf("Solicitado", "En espera", "Finalizado")
    var expanded by mutableStateOf(false)


    var uiState = MutableStateFlow(CitaListState())
        private set

    var uiStateCita = MutableStateFlow(CitaState())
        private set

    var citaId by mutableStateOf(0)
    var concepto by mutableStateOf("")
    var fecha by mutableStateOf("")
    var clienteId by mutableStateOf(0)
    var mecanicoId by mutableStateOf(0)

    private var _state = mutableStateOf(CitaListState())
    val state: State<CitaListState> = _state

    init {
        citaRepository.gestCitas().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Cita = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    uiState.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setCita(id:Int){
        citaId = id
        citaRepository.getCitasbyId(citaId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCita.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCita.update {
                        it.copy(cita = result.data )
                    }
                    concepto = uiStateCita.value.cita!!.concepto
                    fecha = uiStateCita.value.cita!!.fecha
                }
                is Resource.Error -> {
                    uiStateCita.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            citaRepository.putCitas(citaId.toInt(),
                CitaDto(
                    citaId = citaId.toInt(),
                    concepto = concepto,
                    fecha = fecha,
                    uiStateCita.value.cita!!.clienteId,
                    uiStateCita.value.cita!!.mecanicoId
                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            citaRepository.postCitas(
                CitaDto(
                    citaId =0,
                    concepto = concepto,
                    fecha = fecha,
                    clienteId = 0,
                    mecanicoId = 0,

                    )
            )
        }
    }
    fun eliminar(id:Int){
        viewModelScope.launch {
            citaRepository.deleteCitas(id)

            citaRepository.gestCitas().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Cita = result.data ?: emptyList())
                        }
                    }

                    is Resource.Error -> {
                        uiState.update {
                            it.copy(error = result.message ?: "Error desconocido")
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

}