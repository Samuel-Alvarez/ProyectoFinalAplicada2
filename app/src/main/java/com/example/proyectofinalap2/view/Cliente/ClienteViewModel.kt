package com.example.proyectofinalap2.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalap2.data.remote.dto.ClienteDto
import com.example.proyectofinalap2.data.repository.ClienteRepository
import com.example.proyectofinalap2.util.Resource
import com.example.proyectofinalap2.view.Cliente.ClienteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClientesState(
    val isLoading: Boolean = false,
    val cliente: ClienteDto? =  null,
    val error: String = ""
)

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
): ViewModel() {


    var uiState = MutableStateFlow(ClienteListState())
        private set

    var uiStateCliente = MutableStateFlow(ClientesState())
        private set

    var clienteId by mutableStateOf(0)
    var vehiculoId by mutableStateOf(0)
    var nombres by mutableStateOf("")
    var telefono by mutableStateOf("")
    var direccion by mutableStateOf("")

    private var _state = mutableStateOf(ClienteListState())
    val state: State<ClienteListState> = _state

    init {
        clienteRepository.gestClientes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Cliente = result.data ?: emptyList())
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

    fun setCliente(id:Int){
        clienteId = id
        clienteRepository.getClientesbyId(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCliente.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCliente.update {
                        it.copy(cliente = result.data )
                    }
                    nombres = uiStateCliente.value.cliente!!.nombres
                    telefono = uiStateCliente.value.cliente!!.telefono
                    direccion = uiStateCliente.value.cliente!!.direccion
                }
                is Resource.Error -> {
                    uiStateCliente.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            clienteRepository.putClientes(clienteId.toInt(),
                ClienteDto(
                    clienteId = clienteId.toInt(),
                    nombres = nombres,
                    telefono = telefono,
                    direccion = direccion,
                    uiStateCliente.value.cliente!!.vehiculoId,

                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            clienteRepository.postClientes(
                ClienteDto(
                    clienteId =0,
                    vehiculoId = 0,
                    nombres = nombres,
                    telefono = telefono,
                    direccion = direccion,

                )
            )
        }
    }

}