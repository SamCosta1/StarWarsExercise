package com.example.samcosta.starwarsexercise.engine.controller

import com.example.samcosta.starwarsexercise.engine.api.CharactersApi
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by samcosta on 07/01/2019.
 */

class ApiController {

    val charactersApi: CharactersApi

    private val moshi: Moshi
    init {
        moshi = buildMoshi()
        val retrofit = buildRetrofit()

        charactersApi = retrofit.create(CharactersApi::class.java)
    }

    private fun buildMoshi(): Moshi = Moshi.Builder().build()

    private fun buildRetrofit(): Retrofit
            = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://swapi.co/api/")
            .build()


}
