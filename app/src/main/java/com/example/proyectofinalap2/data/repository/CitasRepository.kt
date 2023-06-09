package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.CitaDto
import com.example.proyectofinalap2.data.remote.dto.ClienteDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CitasRepository @Inject constructor(
    private val api: ProyectoFinalApi
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

    suspend fun postCitas(citaDto: CitaDto): CitaDto {
        return api.postCitas(citaDto)
    }
    suspend fun deleteCitas(id: Int): Response<Unit> {
        return api.deletecita(id)
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