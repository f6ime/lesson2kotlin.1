package com.example.lesson2kotlin1.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.lesson2kotlin1.data.remote.models.character.RickAndMortyCharacter

class CharactersDiffUtil : DiffUtil.ItemCallback<RickAndMortyCharacter>() {

    override fun areItemsTheSame(
        oldItem: RickAndMortyCharacter,
        newItem: RickAndMortyCharacter
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RickAndMortyCharacter,
        newItem: RickAndMortyCharacter
    ) = oldItem == newItem
}