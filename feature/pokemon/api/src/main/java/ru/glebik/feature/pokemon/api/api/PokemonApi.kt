package ru.glebik.feature.pokemon.api.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.glebik.feature.pokemon.api.model.response.PokemonDetailResponse
import ru.glebik.feature.pokemon.api.model.response.PokemonListResponse
import ru.glebik.feature.pokemon.api.model.response.PokemonSpritesResponse

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
    ) : PokemonListResponse

    @GET("pokemon-form/{name}")
    suspend fun getPokemonSprite(
        @Path("name") name : String
    ) : PokemonSpritesResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetail(
        @Path("id") id : Int,
    ) : PokemonDetailResponse

}