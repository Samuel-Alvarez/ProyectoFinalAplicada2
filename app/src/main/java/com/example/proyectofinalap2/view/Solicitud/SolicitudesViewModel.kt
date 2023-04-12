package com.example.proyectofinalap2.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.SolicitudDto
import com.example.proyectofinalap2.data.repository.SolicitudesRepository
import com.example.proyectofinalap2.util.Resource
import com.example.proyectofinalap2.view.Solicitud.SolicitudListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SolicitudesState(
    val isLoading: Boolean = false,
    val solicitud: SolicitudDto? =  null,
    val error: String = ""
)

@HiltViewModel
class SolicitudesViewModel @Inject constructor(
    private val solicitudesRepository: SolicitudesRepository
): ViewModel() {
    val solicitudesEstado = listOf("Solicitada", "Finalizada")
    var expanded by mutableStateOf(false)

    var uiState = MutableStateFlow(SolicitudListState())
        private set

    var uiStateSolicitud = MutableStateFlow(SolicitudesState())
        private set

    var solicitudId by mutableStateOf(0)
    var clienteId by mutableStateOf("")
    var mecanicoId by mutableStateOf(0)
    var concepto by mutableStateOf("")
    var fecha by mutableStateOf("")
    var estado by mutableStateOf("")


    private var _state = mutableStateOf(SolicitudListState())
    val state: State<SolicitudListState> = _state

    init {
        solicitudesRepository.gestSolicitudes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Solicitud = result.data ?: emptyList())
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
    

    fun setSolicitud(id:Int){
       solicitudId = id
        solicitudesRepository.getSolicitudesbyId(solicitudId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateSolicitud.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateSolicitud.update {
                        it.copy(solicitud = result.data )
                    }
                    concepto = uiStateSolicitud.value.solicitud!!.concepto
                    fecha = uiStateSolicitud.value.solicitud!!.fecha
                    estado = uiStateSolicitud.value.solicitud!!.estado
                    clienteId = uiStateSolicitud.value.solicitud!!.clienteId.toString()
                }
                is Resource.Error -> {
                    uiStateSolicitud.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            solicitudesRepository.putSolicitudes(solicitudId.toInt(),
                SolicitudDto(
                    solicitudId = solicitudId.toInt(),
                    concepto = concepto,
                    fecha = fecha,
                    estado = estado,
                    clienteId = clienteId.toInt(),
                    uiStateSolicitud.value.solicitud!!.mecanicoId
                )
            )
        }
        solicitudesRepository.gestSolicitudes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Solicitud = result.data ?: emptyList())
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
            solicitudesRepository.postSolicitudes(
                SolicitudDto(
                    mecanicoId = mecanicoId,
                    clienteId = clienteId.toInt(),
                    solicitudId = 0,
                    concepto = concepto,
                    fecha = fecha,
                    estado = estado
                )
            )
        }
        solicitudesRepository.gestSolicitudes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Solicitud = result.data ?: emptyList())
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
            solicitudesRepository.deleteSolicitudes(id)

            solicitudesRepository.gestSolicitudes().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Solicitud = result.data ?: emptyList())
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