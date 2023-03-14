package com.software.home_module.datasource.api

import com.software.home_module.data.datasource.api.HomeService
import com.software.home_module_api.data.api.datasource.HomeApi
import com.software.home_module_api.domain.PokemonEntity
import com.software.home_module_api.domain.PokemonResultEntity
import javax.inject.Inject

class HomeApiImpl @Inject constructor(private val homeService: HomeService) : HomeApi {
    override suspend fun getPokemon(offset: Int, limit: Int): PokemonResultEntity? =
        homeService.getPokemonList(offset, limit)?.toPokemonResultEntity()
}

private fun PokemonResponse.toPokemonResultEntity(): PokemonResultEntity = PokemonResultEntity(
    nextPage = this.next?.toGetPage() ?: 0,
    previousPage = this.previous?.toGetPage() ?: 0,
    pokemonList = this.pokemonList?.map { it.toPokemonEntity() } ?: emptyList()
)

private fun Result.toPokemonEntity() = PokemonEntity(
    id = this.url.toGetId() ?: 0,
    name = this.name.orEmpty(),
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + this.url.toGetId() + ".png"
)

private fun String?.toGetId(): Int? = this?.let { number ->
    POKEMON_REGEX.find(number)?.value?.filter { it.isDigit() }?.toInt()
}


private fun String?.toGetPage(): Int? = this?.let { number ->
    OFFSET_REGEX.find(number)?.value?.toInt()
}

//val POKEMON_REGEX = "/pokemon/(\\d+)/".toRegex()
val POKEMON_REGEX = "\\/([^\\/]+\\/?\$)".toRegex()
val OFFSET_REGEX = "(?<=offset=)\\d+".toRegex()