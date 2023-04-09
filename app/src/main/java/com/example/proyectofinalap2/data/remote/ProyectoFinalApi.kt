package com.example.proyectofinalap2.data.remote

import com.example.proyectofinalap2.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ProyectoFinalApi{

    //Mecanicos
    @GET("api/Mecanicos")
    suspend fun gestMecanicos(): List<MecanicoDto>
    @GET("/api/Mecanicos/{id}")
    suspend fun getMecanicosbyId(@Path("id") id: Int): MecanicoDto
    @PUT("api/Mecanicos/{id}")
    suspend fun putMecanicos(@Path("id") id:Int, @Body mecanicoDto: MecanicoDto): Response<Unit>

    @POST("api/Mecanicos")
    suspend fun postMecanico(@Body mecanicoDto: MecanicoDto): MecanicoDto
    @DELETE("api/Mecanicos/{id}")
    suspend fun deletemecanico(@Path("id") id: Int) : Response<Unit>

    //Citas
    @GET("api/Citas")
    suspend fun gestCitas(): List<CitaDto>
    @GET("api/citas/{id}")
    suspend fun getCitasbyId(@Path("id") id: Int): CitaDto
    @PUT("api/Citas/{id}")
    suspend fun putCitas(@Path("id") id:Int, @Body citaDto: CitaDto): Response<Unit>
    @POST("api/Citas")
    suspend fun postCitas(@Body citaDto: CitaDto): CitaDto
    @DELETE("api/Citas/{id}")
    suspend fun deletecita(@Path("id") id: Int) : Response<Unit>

    //Reportes
    @GET("api/Reportes")
    suspend fun gestReportes(): List<ReporteDto>
    @GET("api/Reportes/{id}")
    suspend fun getReportesbyId(@Path("id") id: Int): ReporteDto
    @PUT("api/Reportes/{id}")
    suspend fun putReportes(@Path("id") id:Int, @Body reporteDto: ReporteDto): Response<Unit>
    @POST("api/Reportes")
    suspend fun postReportes(@Body reporteDto: ReporteDto): ReporteDto
    @DELETE("api/Reportes/{id}")
    suspend fun deletereporte(@Path("id") id: Int) : Response<Unit>

    //Solicitudes
    @GET("api/Solicitudes")
    suspend fun gestSolicitudes(): List<SolicitudDto>
    @GET("/api/Solicitudes/{id}")
    suspend fun getSolicitudesbyId(@Path("id") id: Int): SolicitudDto
    @PUT("api/Solicitudes/{id}")
    suspend fun putSolicitudes(@Path("id") id:Int, @Body solicitudDto: SolicitudDto): Response<Unit>
    @POST("api/Solicitudes")
    suspend fun postSolucitudes(@Body solicitudDto: SolicitudDto): SolicitudDto
    @DELETE("api/Solicitudes/{id}")
    suspend fun deletesolicitud(@Path("id") id: Int) : Response<Unit>


    //Vehiculo
    @GET("api/Vehiculos")
    suspend fun gestVehiculos(): List<VehiculoDto>
    @GET("api/Vehiculos/{id}")
    suspend fun getVehiculosbyId(@Path("id") id: Int): VehiculoDto
    @PUT("api/Vehiculos/{id}")
    suspend fun putVehiculos(@Path("id") id:Int, @Body vehiculoDto: VehiculoDto): Response<Unit>
    @POST("api/Vehiculos")
    suspend fun postVehiculos(@Body vehiculoDto: VehiculoDto): VehiculoDto
    @DELETE("api/Vehiculos/{id}")
    suspend fun deletevehiculo(@Path("id") id: Int) : Response<Unit>

    //Cliente
    @GET("api/Clientes")
    suspend fun gestClientes(): List<ClienteDto>
    @GET("api/Clientes/{id}")
    suspend fun getClientesbyId(@Path("id") id: Int): ClienteDto
    @PUT("api/Clientes/{id}")
    suspend fun putClientes(@Path("id") id:Int, @Body clienteDto: ClienteDto): Response<Unit>
    @POST("api/Clientes")
    suspend fun postClientes(@Body clienteDto: ClienteDto): ClienteDto
    @DELETE("api/Clientes/{id}")
    suspend fun deletecliente(@Path("id") id: Int) : Response<Unit>
}