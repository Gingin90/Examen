package com.example.superheroescomics.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.superheroes.data.local.SuperHeroDAO
import com.example.superheroescomics.data.local.detail.SuperHeroDetailEntity
import com.example.superheroescomics.data.local.list.SuperHeroEntity

@Database(entities = [SuperHeroEntity::class, SuperHeroDetailEntity::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {

    abstract fun getSuperHeroesDAO(): SuperHeroDAO

    companion object {
        @Volatile
        private var INSTANCE: SuperHeroDatabase? = null

        fun getDataBase(context: Context): SuperHeroDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperHeroDatabase::class.java,
                    "super_heroes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}