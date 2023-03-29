package com.example.proyectofinalap2.data.remote

import com.example.proyectofinalap2.data.remote.dto.MecanicoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MecanicoApi{

    @GET("api/Mecanicos")
    suspend fun gestMecanicos(): List<MecanicoDto>

    @GET("/api/mecanicos/{id}")
    suspend fun getMecanicosbyId(@Path("id") id: Int): MecanicoDto

    @PUT("api/Mecanicos/{id}")
    suspend fun putMecanicos(@Path("id") id:Int, @Body mecanicoDto: MecanicoDto): Response<Unit>
}