package com.example.superheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "super_hero_table")
data class SuperHeroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val imageUrl: String,
    val superPower: String,
    val year: Int
)