package com.example.superheroes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperHeroDao {
    //Lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroes(superHeroEntity: List<SuperHeroEntity>)

    @Query("Select * from super_hero_table order by id ASC")
    fun getSuperHeroes() :LiveData<List<SuperHeroEntity>>

    //Detalle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroDetails(superHeroDetailEntity: SuperHeroDetailEntity)

    @Query("Select * from super_hero_detail_table WHERE id = :id")
    fun getSuperHeroDetails(id: Int): LiveData<SuperHeroDetailEntity>
}