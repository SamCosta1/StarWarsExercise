package com.example.samcosta.starwarsexercise.engine

import com.example.samcosta.starwarsexercise.engine.controller.ApiController
import com.example.samcosta.starwarsexercise.engine.controller.CharacterController

/**
 * Created by samcosta on 07/01/2019.
 */

class Engine {
    companion object {
        val engine: Engine = Engine()
    }

    private val apiController = ApiController()

    val characterController = CharacterController(apiController.charactersApi)
}