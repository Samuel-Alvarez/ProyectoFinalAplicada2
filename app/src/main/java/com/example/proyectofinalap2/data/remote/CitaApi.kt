package com.example.proyectofinalap2.data.remote

import com.example.proyectofinalap2.data.remote.dto.CitaDto
import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CitaApi{

    @GET("api/Citas")
    suspend fun gestCitas(): List<CitaDto>

    @GET("/api/citas/{id}")
    suspend fun getCitasbyId(@Path("id") id: Int): CitaDto

    @PUT("api/Citas/{id}")
    suspend fun putCitas(@Path("id") id:Int, @Body citaDto: CitaDto): Response<Unit>
}