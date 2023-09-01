package com.example.superheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "super_hero_detail_table")
data class SuperHeroDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val imageUrl: String,
    val superPower: String,
    val year: Int,
    val color: String,
    val translate: Boolean
)
