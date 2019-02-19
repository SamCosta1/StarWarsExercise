package com.example.samcosta.starwarsexercise.engine.controller

import com.example.samcosta.starwarsexercise.engine.api.ApiCallCompletion
import com.example.samcosta.starwarsexercise.engine.api.ApiResponse
import com.example.samcosta.starwarsexercise.engine.api.CharactersApi
import com.example.samcosta.starwarsexercise.engine.api.enqueue
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacterResponse

/**
 * Created by samcosta on 07/01/2019.
 *
 * This layer is where any business logic would happen. For example it might also have a dependency
 * on a database controller which could be used to maintain a local cache. The specification as it is
 * does not require this so it remains blank,
 */

class CharacterController(private val characterApi: CharactersApi) {

    fun getCharacters(completion: ApiCallCompletion<StarWarsCharacterResponse>) {
        characterApi.getCharacters(1).enqueue(completion)
    }
}