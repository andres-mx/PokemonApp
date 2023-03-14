package com.software.home_module_api.domain

data class PokemonResultEntity(
    val nextPage: Int = 0,
    val previousPage: Int = 0,
    val pokemonList: List<PokemonEntity> = emptyList()
)