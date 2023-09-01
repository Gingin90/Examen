package com.example.superheroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SuperHeroEntity::class, SuperHeroDetailEntity::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {

    abstract fun getSuperHeroesDAO(): SuperHeroDao

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
                    "super_heroe_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}