package com.example.samcosta.starwarsexercise.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.samcosta.starwarsexercise.R
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacter
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacterSummary
import com.example.samcosta.starwarsexercise.util.UnitUtils

/**
 * Created by samcosta on 08/01/2019.
 */
class CharacterDetailFragment: Fragment() {

    companion object {
        const val TAG = "CharacterDetailFragment"
        private val CHARACTER_ARG_KEY = "character_arg_key"
        fun newInstance(character: StarWarsCharacter): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()

            val args = Bundle()
            args.putParcelable(CHARACTER_ARG_KEY, character.toSummary())

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterSummary = arguments?.getParcelable<StarWarsCharacterSummary>(CHARACTER_ARG_KEY) ?: return

        val nameField: TextView = view.findViewById(R.id.characterName)
        val massField: TextView = view.findViewById(R.id.characterMass)
        val heightField: TextView = view.findViewById(R.id.characterHeight)
        val createdDateField: TextView = view.findViewById(R.id.characterCreatedDate)

        nameField.text = characterSummary.name
        massField.text = characterSummary.mass
        heightField.text = UnitUtils.convertCmToMeters(characterSummary.height)
        createdDateField.text = characterSummary.created
    }
}