package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MecanicosRepository @Inject constructor(
    private val api: ProyectoFinalApi
) {
    fun gestMecanicos(): Flow<Resource<List<MecanicoDto>>> = flow {
        try {
            emit(Resource.Loading())
            val mecanico = api.gestMecanicos()
            emit(Resource.Success(mecanico))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putMecanicos(id:Int, mecanicoDto: MecanicoDto){
        api.putMecanicos(id, mecanicoDto)
    }

    suspend fun postMecanico(mecanicoDto: MecanicoDto):MecanicoDto{
        return api.postMecanico(mecanicoDto)
    }
    suspend fun deleteMecanico(id: Int): Response<Unit> {
        return api.deletemecanico(id)
    }
    fun getMecanicosbyId(id: Int): Flow<Resource<MecanicoDto>> = flow {
        try {
            emit(Resource.Loading())
            val mecanicos = api.getMecanicosbyId(id)
            emit(Resource.Success(mecanicos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}