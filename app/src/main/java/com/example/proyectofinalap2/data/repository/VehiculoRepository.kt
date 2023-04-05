package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.VehiculoDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VehiculoRepository @Inject constructor(
    private val api: ProyectoFinalApi
) {
    fun gestVehiculos(): Flow<Resource<List<VehiculoDto>>> = flow {
        try {
            emit(Resource.Loading())
            val vehiculo = api.gestVehiculos()
            emit(Resource.Success(vehiculo))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putVehiculos(id:Int, vehiculoDto: VehiculoDto){
        api.putVehiculos(id, vehiculoDto)
    }

    fun getVehiculosbyId(id: Int): Flow<Resource<VehiculoDto>> = flow {
        try {
            emit(Resource.Loading())
            val vehiculos = api.getVehiculosbyId(id)
            emit(Resource.Success(vehiculos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}