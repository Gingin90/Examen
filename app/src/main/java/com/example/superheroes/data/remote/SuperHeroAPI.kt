package com.example.superheroes.data.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroAPI {

    //Lista
    @GET("superheroes/")
    suspend fun getDataSuperHero(): Response<List<SuperHero>>

    //Detalle
    @GET("superheroes/{id}")
    suspend fun getSuperHeroDetails(@Path("id") id: Int): Response<SuperHeroDetail>
}