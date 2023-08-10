package com.software.home_module.data.repository

import com.software.home_module_api.data.datasource.api.HomeApi
import com.software.home_module_api.data.repository.HomeRepository
import com.software.home_module_api.domain.PokemonResultEntity
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {
    override suspend fun getPokemonList(): PokemonResultEntity =
        homeApi.getPokemon(0, 20) ?: PokemonResultEntity()
}