package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.ClienteDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val api: ProyectoFinalApi
) {
    fun gestClientes(): Flow<Resource<List<ClienteDto>>> = flow {
        try {
            emit(Resource.Loading())
            val cliente = api.gestClientes()
            emit(Resource.Success(cliente))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putClientes(id:Int, clienteDto: ClienteDto){
        api.putClientes(id, clienteDto)
    }

    suspend fun postClientes(clienteDto: ClienteDto): ClienteDto {
        return api.postClientes(clienteDto)
    }

    fun getClientesbyId(id: Int): Flow<Resource<ClienteDto>> = flow {
        try {
            emit(Resource.Loading())
            val clientes = api.getClientesbyId(id)
            emit(Resource.Success(clientes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}