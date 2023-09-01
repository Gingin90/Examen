package com.example.superheroes

import com.example.superheroes.data.remote.SuperHero
import com.example.superheroes.data.remote.SuperHeroDetail
import com.example.superheroes.data.transformToDetailEntity
import com.example.superheroes.data.transformToEntity

import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun transformToEntity() {
        //Given(dado este valor)
        val superHero = SuperHero(1, "Sirenita", "Planeta Tierra", "wwww.imageSH.cl", "Water", 1990)

        //When(hago esta acción)
        val resultadoTransformacionSuperHero = superHero.transformToEntity()

        //Then(espero este resultado)
        assertEquals(superHero.id, resultadoTransformacionSuperHero.id)
        assertEquals(superHero.name, resultadoTransformacionSuperHero.name)
        assertEquals(superHero.origin, resultadoTransformacionSuperHero.origin)
        assertEquals(superHero.imageUrl, resultadoTransformacionSuperHero.imageUrl)
        assertEquals(superHero.superPower, resultadoTransformacionSuperHero.superPower)
        assertEquals(superHero.year, resultadoTransformacionSuperHero.year)
    }

    @Test
    fun transformToDetailEntity() {
        //Given(dado este valor)
        val superHeroDetail =
            SuperHeroDetail(6, "Alien", "Marte", "wwww.imageSHD.cl", "Thunder", 1980, "blue", true)

        //When(hago esta acción)
        val resultadoTransformacionSuperHeroDetail = superHeroDetail.transformToDetailEntity()

        //Then(espero este resultado)
        assertEquals(superHeroDetail.id, resultadoTransformacionSuperHeroDetail.id)
        assertEquals(superHeroDetail.name, resultadoTransformacionSuperHeroDetail.name)
        assertEquals(superHeroDetail.origin, resultadoTransformacionSuperHeroDetail.origin)
        assertEquals(superHeroDetail.imageUrl, resultadoTransformacionSuperHeroDetail.imageUrl)
        assertEquals(superHeroDetail.superPower, resultadoTransformacionSuperHeroDetail.superPower)
        assertEquals(superHeroDetail.year, resultadoTransformacionSuperHeroDetail.year)
        assertEquals(superHeroDetail.color, resultadoTransformacionSuperHeroDetail.color)
        assertEquals(superHeroDetail.translate, resultadoTransformacionSuperHeroDetail.translate)
    }
}