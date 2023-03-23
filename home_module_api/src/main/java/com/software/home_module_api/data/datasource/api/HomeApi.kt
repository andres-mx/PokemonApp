package com.software.home_module_api.data.datasource.api

import com.software.home_module_api.domain.PokemonResultEntity

interface HomeApi {
    suspend fun getPokemon(offset: Int, limit: Int): PokemonResultEntity?
}