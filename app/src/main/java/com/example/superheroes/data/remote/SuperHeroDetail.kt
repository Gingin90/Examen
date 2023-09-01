package com.example.superheroes.data.remote

import com.google.gson.annotations.SerializedName

data class SuperHeroDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val imageUrl: String,
    @SerializedName("poder") val superPower: String,
    @SerializedName("año_creacion") val year: Int,
    @SerializedName("color") val color: String,
    @SerializedName("traduccion") val translate: Boolean
)