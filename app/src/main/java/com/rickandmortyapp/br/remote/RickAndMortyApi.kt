package com.rickandmortyapp.br.remote

import com.gustafah.android.mockinterceptor.Mock
import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @Mock("detail_response.json")
    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<Character>

    @Mock("list_response.json")
    @GET("character")
    fun getCharactersList(): Call<CharacterList>
}