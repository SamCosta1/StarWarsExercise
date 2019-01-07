package com.example.samcosta.starwarsexercise.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.samcosta.starwarsexercise.engine.model.StarWarsCharacter
import android.view.LayoutInflater
import android.view.View
import com.example.samcosta.starwarsexercise.R
import kotlinx.android.synthetic.main.cell_character.view.*

typealias OnCharacterSelectedListener = (StarWarsCharacter) -> Unit

class CharactersListAdapter(private val onItemSelectedListener: OnCharacterSelectedListener): RecyclerView.Adapter<CharactersViewHolder>() {

    var data: List<StarWarsCharacter>? = null; set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_character, parent, false)
        return CharactersViewHolder(view, this::onCellClicked)
    }

    private fun onCellClicked(index: Int) {
        if (index >= 0 && index < itemCount) {
            data?.let {
                onItemSelectedListener(it[index])
            }
        }
    }
    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        data?.let { data ->
            holder.bind(data[position])
        }
    }

}

class CharactersViewHolder(itemView: View, onClickedListener: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onClickedListener(adapterPosition)
        }
    }

    fun bind(starWarsCharacter: StarWarsCharacter) {
        itemView.characterName.text = starWarsCharacter.name
    }
}
