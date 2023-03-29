package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.CitaApi
import com.example.proyectofinalap2.data.remote.dto.CitaDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CitasRepository @Inject constructor(
    private val api: CitaApi
) {
    fun gestCitas(): Flow<Resource<List<CitaDto>>> = flow {
        try {
            emit(Resource.Loading())
            val cita = api.gestCitas()
            emit(Resource.Success(cita))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putCitas(id:Int, citaDto: CitaDto){
        api.putCitas(id, citaDto)
    }

    fun getCitasbyId(id: Int): Flow<Resource<CitaDto>> = flow {
        try {
            emit(Resource.Loading())
            val citas = api.getCitasbyId(id)
            emit(Resource.Success(citas))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}