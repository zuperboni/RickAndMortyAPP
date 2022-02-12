package com.rickandmortyapp.br.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterList(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val characters: List<Character>
)