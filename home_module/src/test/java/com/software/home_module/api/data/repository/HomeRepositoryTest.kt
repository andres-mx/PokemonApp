package com.software.home_module.api.data.repository

import com.software.home_module.data.repository.HomeRepositoryImpl
import com.software.home_module_api.data.api.datasource.HomeApi
import com.software.home_module_api.data.repository.HomeRepository
import com.software.home_module_api.domain.PokemonEntity
import com.software.home_module_api.domain.PokemonResultEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeRepositoryTest {
    private lateinit var homeRepository: HomeRepository
    private val homeApi: HomeApi = mock()

    @Before
    fun setUp() {
        homeRepository = HomeRepositoryImpl(homeApi)
    }

    @Test
    fun `GIVEN a pokemon list WHEN getPokemonList is requested THEN pokemon list is not empty`() =
        runTest {
            //Given
            whenever(homeApi.getPokemon(0, 20)).thenReturn(getPokemonList())
            //When
            val response = homeRepository.getPokemonList()
            //Then
            Assert.assertNotNull(response)
            Assert.assertTrue(response.pokemonList.isNotEmpty())
        }

    @Test
    fun `GIVEN a pokemon list WHEN getPokemonList is requested THEN pokemon list is empty`() =
        runTest {
            //Given
            whenever(homeApi.getPokemon(0, 20)).thenReturn(getPokemonEmptyList())
            //When
            val response = homeRepository.getPokemonList()
            //Then
            Assert.assertNotNull(response)
            Assert.assertTrue(response.pokemonList.isEmpty())
        }


    private fun getPokemonList(): PokemonResultEntity = PokemonResultEntity(
        nextPage = 20,
        previousPage = 0,
        pokemonList = listOf(
            PokemonEntity(
                name = "spearow",
                id = 1,
                image = "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            PokemonEntity(
                name = "fearow",
                id = 2,
                image = "https://pokeapi.co/api/v2/pokemon/2/"
            )
        )
    )

    private fun getPokemonEmptyList(): PokemonResultEntity = PokemonResultEntity(
        nextPage = 20,
        previousPage = 0,
        pokemonList = listOf()
    )

}