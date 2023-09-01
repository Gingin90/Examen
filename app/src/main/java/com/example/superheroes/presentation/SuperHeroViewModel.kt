package com.example.superheroes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes.data.Repository
import com.example.superheroes.data.local.SuperHeroDao
import com.example.superheroes.data.local.SuperHeroDatabase

import com.example.superheroes.data.remote.SuperHeroRetrofit
import kotlinx.coroutines.launch

class SuperHeroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val api = SuperHeroRetrofit.getSuperHeroRetrofit()
        val cellPhoneDataBase: SuperHeroDao =
            SuperHeroDatabase.getDataBase(application).getSuperHeroesDAO()
        repository = Repository(api, cellPhoneDataBase)
    }

    //Lista
    fun superHeroesLiveData() = repository.getSuperHeroesFromEntity()

    fun getSuperHeroesViewModel() = viewModelScope.launch { repository.getSuperHeroes() }

    //Detalle
    fun superHeroDetailLiveData(id: Int) = repository.getSuperHeroDetailsFromEntity(id)

    fun superHeroDetailsViewModel(id: Int) =
        viewModelScope.launch { repository.getSuperHeroDetails(id) }
}