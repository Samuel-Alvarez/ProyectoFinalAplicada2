package com.example.proyectofinalap2.view.Reporte

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.ReporteDto
import com.example.proyectofinalap2.data.repository.ReportesRepository
import com.example.proyectofinalap2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReportesState(
    val isLoading: Boolean = false,
    val reporte: ReporteDto? =  null,
    val error: String = ""
)

@HiltViewModel
class ReportesViewModel @Inject constructor(
    private val reportesRepository: ReportesRepository
): ViewModel() {


    var uiState = MutableStateFlow(ReporteListStates())
        private set

    var uiStateReporte = MutableStateFlow(ReportesState())
        private set

    var reporteId by mutableStateOf(0)
    var clienteId by mutableStateOf(0)
    var mecanicoId by mutableStateOf(0)
    var concepto by mutableStateOf("")
    var fecha by mutableStateOf("")


    private var _state = mutableStateOf(ReporteListStates())
    val state: State<ReporteListStates> = _state


    fun setReporte(id:Int){
        reporteId = id
        reportesRepository.getReportesbyId(reporteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateReporte.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateReporte.update {
                        it.copy(reporte = result.data )
                    }
                    concepto = uiStateReporte.value.reporte!!.concepto
                    fecha = uiStateReporte.value.reporte!!.fecha
                }
                is Resource.Error -> {
                    uiStateReporte.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            reportesRepository.putReportes(reporteId.toInt(),
                ReporteDto(
                    reporteId = reporteId.toInt(),
                    concepto = concepto,
                    fecha = fecha,
                    uiStateReporte.value.reporte!!.clienteId,
                    uiStateReporte.value.reporte!!.mecanicoId
                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            reportesRepository.postReportes(
                ReporteDto(
                    mecanicoId = 0,
                    clienteId = 0,
                    reporteId = 0,
                    concepto = concepto,
                    fecha = fecha
                )
            )
        }
    }
    fun eliminar(id:Int){
        viewModelScope.launch {
            reportesRepository.deleteReportes(id)

            reportesRepository.gestReportes().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Reporte = result.data ?: emptyList())
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