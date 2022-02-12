package com.rickandmortyapp.br.remote

import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<Character>

    @GET("character")
    fun getCharactersList(): Call<CharacterList>
}