package com.example.samcosta.starwarsexercise.engine.api

/**
 * Created by samcosta on 07/01/2019.
 */

import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacterResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface CharactersApi {

    @POST("people")
    fun getCharacters(@Query("page") page: Int = 1): Call<StarWarsCharacterResponse>
}