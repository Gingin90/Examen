package com.example.superheroescomics.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroRetrofit {
    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"

        fun getSuperHeroRetrofit(): SuperHeroAPI {
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return mRetrofit.create(SuperHeroAPI::class.java)
        }
    }
}