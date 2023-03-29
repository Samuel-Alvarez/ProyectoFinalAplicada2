package com.example.proyectofinalap2.di

import com.example.proyectofinalap2.data.remote.MecanicoApi
import com.example.proyectofinalap2.data.repository.MecanicosRepository
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
    fun provideTickets(moshi: Moshi): MecanicoApi {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MecanicoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMecanicosRepository(mecanicosApi: MecanicoApi): MecanicosRepository {
        return MecanicosRepository(mecanicosApi)
    }
}