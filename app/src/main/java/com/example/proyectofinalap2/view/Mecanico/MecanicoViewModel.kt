package com.example.proyectofinalap2.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import com.example.proyectofinalap2.data.repository.MecanicosRepository
import com.example.proyectofinalap2.util.Resource
import com.example.proyectofinalap2.view.Mecanico.MecanicoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MecanicosState(
    val isLoading: Boolean = false,
    val mecanico: MecanicoDto ? =  null,
    val error: String = ""
)

@HiltViewModel
class MecanicoViewModel @Inject constructor(
    private val mecanicosRepository: MecanicosRepository
): ViewModel() {


    var uiState = MutableStateFlow(MecanicoListState())
        private set

    var uiStateMecanico = MutableStateFlow(MecanicosState())
        private set

    var mecanicoId by mutableStateOf(0)
    var nombres by mutableStateOf("")
    var area by mutableStateOf("")
    var telefono by mutableStateOf("")
    var disponible by mutableStateOf(0)

    private var _state = mutableStateOf(MecanicoListState())
    val state: State<MecanicoListState> = _state

    init {
        mecanicosRepository.gestMecanicos().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Mecanico = result.data ?: emptyList())
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

    fun setMecanico(id:Int){
        mecanicoId = id
        mecanicosRepository.getMecanicosbyId(mecanicoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateMecanico.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateMecanico.update {
                        it.copy(mecanico = result.data )
                    }
                    nombres = uiStateMecanico.value.mecanico!!.nombres
                    area = uiStateMecanico.value.mecanico!!.area
                    disponible = uiStateMecanico.value.mecanico!!.disponible
                    telefono = uiStateMecanico.value.mecanico!!.telefono
                }
                is Resource.Error -> {
                    uiStateMecanico.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            mecanicosRepository.putMecanicos(mecanicoId.toInt(),
                MecanicoDto(
                    mecanicoId = mecanicoId.toInt(),
                    nombres = nombres,
                    area = area,
                    telefono = telefono,
                    uiStateMecanico.value.mecanico!!.disponible
                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            mecanicosRepository.postMecanico(
                MecanicoDto(
                    mecanicoId = 0,
                    nombres = nombres,
                    area = area,
                    telefono = telefono,
                    disponible = disponible,
                )
            )
        }
    }
    fun eliminar(id:Int){
        viewModelScope.launch {
            mecanicosRepository.deleteMecanico(id)

            mecanicosRepository.gestMecanicos().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Mecanico = result.data ?: emptyList())
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