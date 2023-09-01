package com.example.superheroes.data

import com.example.superheroes.data.local.SuperHeroDetailEntity
import com.example.superheroes.data.local.SuperHeroEntity
import com.example.superheroes.data.remote.SuperHero
import com.example.superheroes.data.remote.SuperHeroDetail

fun SuperHero.transformToEntity(): SuperHeroEntity =
    SuperHeroEntity(this.id, this.name, this.origin, this.imageUrl, this.superPower, this.year)

fun SuperHeroDetail.transformToDetailEntity(): SuperHeroDetailEntity =
    SuperHeroDetailEntity(
        this.id,
        this.name,
        this.origin,
        this.imageUrl,
        this.superPower,
        this.year,
        this.color,
        this.translate
    )
