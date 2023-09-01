package com.example.superheroes.data.remote

import com.google.gson.annotations.SerializedName

data class SuperHero(
   @SerializedName("id") val id: Int,
   @SerializedName("nombre") val name: String,
   @SerializedName("origen") val origin: String,
   @SerializedName("imagenLink")val imageUrl: String,
   @SerializedName("poder")val superPower: String,
   @SerializedName("Año_creacion")val year: Int
)