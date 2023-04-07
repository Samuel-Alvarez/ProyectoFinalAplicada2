package com.example.proyectofinalap2.di

import com.example.proyectofinalap2.data.remote.ProyectoFinalApi
import com.example.proyectofinalap2.data.repository.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTickets(moshi: Moshi): ProyectoFinalApi {
        return Retrofit.Builder()
            .baseUrl("https://aplicada2api20230407141815.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ProyectoFinalApi::class.java)
    }

    @Provides
    @Singleton
    //Mecanico
    fun provideMecanicosRepository(mecanicosApi: ProyectoFinalApi): MecanicosRepository {
        return MecanicosRepository(mecanicosApi)
    }

    //Cliente
    fun provideClientesRepository(clientesApi: ProyectoFinalApi): ClienteRepository {
        return ClienteRepository(clientesApi)
    }

    //Cita
    fun provideCitasRepository(citasApi: ProyectoFinalApi): CitasRepository {
        return CitasRepository(citasApi)
    }

    //Reporte
    fun provideReportesRepository(reportesApi: ProyectoFinalApi): ReportesRepository {
        return ReportesRepository(reportesApi)
    }

    //Solicitud
    fun provideSolicitudesRepository(solicitudApi: ProyectoFinalApi): SolicitudesRepository{
        return SolicitudesRepository(solicitudApi)
    }

    //Vehiculo
    fun provideVehiculosRepository(vehiculoApi: ProyectoFinalApi): VehiculoRepository{
        return VehiculoRepository(vehiculoApi)
    }

}