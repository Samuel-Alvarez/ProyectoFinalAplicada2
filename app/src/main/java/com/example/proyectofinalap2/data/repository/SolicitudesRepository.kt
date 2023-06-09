package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.SolicitudDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class SolicitudesRepository @Inject constructor(
    private val api: ProyectoFinalApi
) {
    fun gestSolicitudes(): Flow<Resource<List<SolicitudDto>>> = flow {
        try {
            emit(Resource.Loading())
            val solicitud = api.gestSolicitudes()
            emit(Resource.Success(solicitud))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putSolicitudes(id:Int, solicitudDto: SolicitudDto){
        api.putSolicitudes(id, solicitudDto)
    }
    suspend fun postSolicitudes(solicitudDto: SolicitudDto): SolicitudDto {
        return api.postSolucitudes(solicitudDto)
    }
    suspend fun deleteSolicitudes(id: Int): Response<Unit> {
        return api.deletesolicitud(id)
    }

    fun getSolicitudesbyId(id: Int): Flow<Resource<SolicitudDto>> = flow {
        try {
            emit(Resource.Loading())
            val solicitudes = api.getSolicitudesbyId(id)
            emit(Resource.Success(solicitudes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}