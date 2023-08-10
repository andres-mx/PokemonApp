package com.software.home_module_api.data.repository

import com.software.home_module_api.domain.PokemonResultEntity

interface HomeRepository {
    suspend fun getPokemonList(): PokemonResultEntity
}