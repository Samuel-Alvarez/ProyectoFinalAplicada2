package com.example.proyectofinalap2.data.repository

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.remote.dto.ReporteDto
import com.example.proyectofinalap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReportesRepository @Inject constructor(
    private val api: ProyectoFinalApi
) {
    fun gestReportes(): Flow<Resource<List<ReporteDto>>> = flow {
        try {
            emit(Resource.Loading())
            val reporte = api.gestReportes()
            emit(Resource.Success(reporte))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putReportes(id:Int, reporteDto: ReporteDto){
        api.putReportes(id, reporteDto)
    }

    fun getReportesbyId(id: Int): Flow<Resource<ReporteDto>> = flow {
        try {
            emit(Resource.Loading())
            val reportes = api.getReportesbyId(id)
            emit(Resource.Success(reportes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}