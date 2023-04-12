package com.example.proyectofinalap2.view.Vehiculo

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.ReporteDto
import com.example.proyectofinalap2.data.remote.dto.VehiculoDto
import com.example.proyectofinalap2.data.repository.ReportesRepository
import com.example.proyectofinalap2.data.repository.VehiculoRepository
import com.example.proyectofinalap2.util.Resource
import com.example.proyectofinalap2.view.Reporte.ReporteListStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VehiculoState(
    val isLoading: Boolean = false,
    val vehiculo: VehiculoDto? =  null,
    val error: String = ""
)

@HiltViewModel
class VehiculosViewModel @Inject constructor(
    private val vehiculoRepository: VehiculoRepository
): ViewModel() {

    var uiState = MutableStateFlow(VehichuloListState())
        private set

    var uiStateVehiculo = MutableStateFlow(VehiculoState())
        private set

    var vehiculoId by mutableStateOf(0)
    var modelo by mutableStateOf("")
    var marca by mutableStateOf("")
    var year by mutableStateOf("")

    private var _state = mutableStateOf(VehichuloListState())
    val state: State<VehichuloListState> = _state

    init {
        vehiculoRepository.gestVehiculos().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Vehiculo = result.data ?: emptyList())
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


    fun setVehiculo(id:Int){
        vehiculoId = id
        vehiculoRepository.getVehiculosbyId(vehiculoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateVehiculo.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateVehiculo.update {
                        it.copy(vehiculo = result.data )
                    }
                    marca = uiStateVehiculo.value.vehiculo!!.marca
                    year = uiStateVehiculo.value.vehiculo!!.year
                }
                is Resource.Error -> {
                    uiStateVehiculo.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            vehiculoRepository.putVehiculos(vehiculoId.toInt(),
                VehiculoDto(
                    vehiculoId = vehiculoId.toInt(),
                    modelo = modelo,
                    marca = marca,
                    year = year
                )
            )
        }
        vehiculoRepository.gestVehiculos().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Vehiculo = result.data ?: emptyList())
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

    fun guardar(){
        viewModelScope.launch {
            vehiculoRepository.postVehiculos(
                VehiculoDto(
                    vehiculoId = 0,
                    modelo = modelo,
                    marca = marca,
                    year = year
                )
            )
        }
        vehiculoRepository.gestVehiculos().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Vehiculo = result.data ?: emptyList())
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
    fun eliminar(id:Int){
        viewModelScope.launch {
            vehiculoRepository.deleteVehiculo(id)

            vehiculoRepository.gestVehiculos().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Vehiculo = result.data ?: emptyList())
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