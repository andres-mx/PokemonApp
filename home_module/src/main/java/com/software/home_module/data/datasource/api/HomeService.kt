package com.software.home_module.data.datasource.api

import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): PokemonResponse?
}