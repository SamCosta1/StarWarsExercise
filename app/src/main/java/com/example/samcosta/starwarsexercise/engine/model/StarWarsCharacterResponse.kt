package com.example.samcosta.starwarsexercise.engine.model

/**
 * Created by samcosta on 07/01/2019.
 */
data class StarWarsCharacterResponse(val count: Int, val next: String, val results: List<StarWarsCharacter>)