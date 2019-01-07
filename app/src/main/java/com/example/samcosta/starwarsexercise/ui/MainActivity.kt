package com.example.samcosta.starwarsexercise.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.samcosta.starwarsexercise.R
import com.example.samcosta.starwarsexercise.engine.Engine
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var charactersRecyclerView: RecyclerView
    private val charactersAdapter = CharactersListAdapter(this::onCharacterClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersRecyclerView = findViewById(R.id.charactersRecycler)
        setupRecyclerView()
        retrieveCharacters()

    }

    private fun retrieveCharacters() {
        Engine.engine.characterController.getCharacters {
            if (isDestroyed) {
                return@getCharacters
            }

            if (it.isSuccess) {
                charactersAdapter.data = it.body?.results
            } else {
                showError()
            }
        }
    }

    private fun showError() {

    }

    private fun onCharacterClicked(character: StarWarsCharacter) {
        Toast.makeText(this, character.name, Toast.LENGTH_LONG).show()
    }

    private fun setupRecyclerView() {
        charactersRecyclerView.adapter = charactersAdapter
        charactersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
