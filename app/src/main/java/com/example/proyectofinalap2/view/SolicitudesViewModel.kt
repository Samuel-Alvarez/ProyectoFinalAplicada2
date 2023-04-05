package com.example.proyectofinalap2.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import com.example.proyectofinalap2.data.remote.dto.SolicitudDto
import com.example.proyectofinalap2.data.repository.MecanicosRepository
import com.example.proyectofinalap2.data.repository.SolicitudesRepository
import com.example.proyectofinalap2.util.Resource
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


    var uiState = MutableStateFlow(SolicitudListState())
        private set

    var uiStateSolicitud = MutableStateFlow(SolicitudesState())
        private set

    var solicitudId by mutableStateOf(0)
    var clienteId by mutableStateOf(0)
    var mecanicoId by mutableStateOf(0)
    var concepto by mutableStateOf("")
    var fecha by mutableStateOf("")


    private var _state = mutableStateOf(SolicitudListState())
    val state: State<SolicitudListState> = _state
    

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
                    uiStateSolicitud.value.solicitud!!.fecha,
                    uiStateSolicitud.value.solicitud!!.clienteId,
                    uiStateSolicitud.value.solicitud!!.mecanicoId
                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            solicitudesRepository.postSolicitudes(
                SolicitudDto(
                    mecanicoId = 0,
                    clienteId = 0,
                    solicitudId = 0,
                    concepto = concepto,
                    fecha = fecha
                )
            )
        }
    }

}