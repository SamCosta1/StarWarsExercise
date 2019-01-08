package com.example.samcosta.starwarsexercise.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.example.samcosta.starwarsexercise.R
import com.example.samcosta.starwarsexercise.engine.Engine
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacter

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_SUCCESS_KEY = "request_success_key"
    }
    private lateinit var charactersRecyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var tryAgain: View
    private var requestSuccessful = false

    private val charactersAdapter = CharactersListAdapter(this::onCharacterClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersRecyclerView = findViewById(R.id.charactersRecycler)
        loader = findViewById(R.id.loader)
        tryAgain = findViewById(R.id.tryAgain)

        tryAgain.setOnClickListener {
            retrieveCharacters()
        }

        setupRecyclerView()
        retrieveCharacters()

        if (savedInstanceState != null) {
            requestSuccessful = savedInstanceState.getBoolean(REQUEST_SUCCESS_KEY)
            tryAgain.visibility = if (requestSuccessful) View.VISIBLE else View.GONE
        }
    }


    private fun retrieveCharacters() {
        loader.visibility = View.VISIBLE
        tryAgain.visibility = View.GONE

        Engine.engine.characterController.getCharacters {
            if (isDestroyed) {
                return@getCharacters
            }

            loader.visibility = View.GONE
            requestSuccessful = it.isSuccess

            if (it.isSuccess) {
                charactersAdapter.data = it.body?.results
            } else {
                showError()
            }
        }
    }

    private fun showError() {
        tryAgain.visibility = View.VISIBLE
        RequestFailedDialogFragment().show(fragmentManager, RequestFailedDialogFragment.TAG)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBoolean(REQUEST_SUCCESS_KEY, requestSuccessful)
    }

    private fun onCharacterClicked(character: StarWarsCharacter) {
        val fragment = CharacterDetailFragment.newInstance(character)

        fragmentManager
                .beginTransaction()
                .add(R.id.childFragmentContainer, fragment, CharacterDetailFragment.TAG)
                .addToBackStack(CharacterDetailFragment.TAG)
                .commit()

    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        charactersRecyclerView.adapter = charactersAdapter
        charactersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
