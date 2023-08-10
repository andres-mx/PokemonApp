package com.software.home_module.api.data.datasource.api

import com.software.home_module.data.datasource.api.HomeApiImpl
import com.software.home_module.data.datasource.api.HomeService
import com.software.home_module.data.datasource.api.PokemonResponse
import com.software.home_module.data.datasource.api.Result
import com.software.home_module_api.data.datasource.api.HomeApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeApiTest {
    private lateinit var homeApi: HomeApi
    private val homeService: HomeService = mock()

    @Before
    fun setUp() {
        homeApi = HomeApiImpl(homeService)
    }

    @Test
    fun `GIVEN a pokemon list WHEN getPokemon is requested THEN pokemon list is returned`() =
        runTest {
            //Given
            whenever(homeService.getPokemonList(0, 20)).thenReturn(getPokemonList())
            //When
            val response = homeApi.getPokemon(0, 20)
            //Then
            Assert.assertNotNull(response)
            Assert.assertEquals(response?.pokemonList?.size, 2)
        }

    @Test
    fun `GIVEN a pokemon list WHEN getPokemon is requested THEN pokemon list is empty`() =
        runTest {
            //Given
            whenever(homeService.getPokemonList(0, 20)).thenReturn(getPokemonEmptyList())
            //When
            val response = homeApi.getPokemon(0, 20)
            //Then
            Assert.assertNotNull(response)
            Assert.assertTrue(response?.pokemonList?.isEmpty() == true)
        }

    @Test
    fun `GIVEN a pokemon list WHEN getPokemon is requested THEN pokemon list is null`() =
        runTest {
            //Given
            whenever(homeService.getPokemonList(0, 20)).thenReturn(null)
            //When
            val response = homeApi.getPokemon(0, 20)
            //Then
            Assert.assertNull(response)
        }


    private fun getPokemonList(): PokemonResponse = PokemonResponse(
        count = 1281,
        next = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
        previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
        pokemonList = listOf(
            Result(
                name = "spearow",
                url = "https://pokeapi.co/api/v2/pokemon/312/"
            ),
            Result(
                name = "fearow",
                url = "https://pokeapi.co/api/v2/pokemon/1/"

            )
        )
    )

    private fun getPokemonEmptyList(): PokemonResponse = PokemonResponse(
        count = 1281,
        next = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
        previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
        pokemonList = listOf()
    )
}