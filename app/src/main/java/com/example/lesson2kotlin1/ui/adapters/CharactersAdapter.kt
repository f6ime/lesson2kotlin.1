package com.example.lesson2kotlin1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.diffutils.CharactersDiffUtil
import com.example.lesson2kotlin1.common.extension.setImage
import com.example.lesson2kotlin1.models.character.RickAndMortyCharacter
import com.example.lesson2kotlin1.databinding.ItemCharactersHolderBinding

class CharactersAdapter(private val onItemCharactersClick: (id: Int) -> Unit) :
    PagingDataAdapter<RickAndMortyCharacter, CharactersAdapter.CharacterViewHolder>(
        CharacterComparator
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharactersHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(private val binding: ItemCharactersHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: RickAndMortyCharacter) {
            binding.apply {
                imCharacter.setImage(character.image)
                tvCharacter.text = character.name
                tvStatus.text = character.status
                tvSpecies.text = character.species

                when (character.status) {
                    "Alive" -> {
                        imStatus.setImageResource(R.drawable.alive_status)
                    }
                    "Dead" -> {
                        imStatus.setImageResource(R.drawable.dead_status)
                    }
                    "Unknown" -> {
                        imStatus.setImageResource(R.drawable.unknown_status)
                    }
                }
                root.setOnClickListener {
                    onItemCharactersClick(character.id)
                }
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<RickAndMortyCharacter>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyCharacter,
            newItem: RickAndMortyCharacter
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RickAndMortyCharacter,
            newItem: RickAndMortyCharacter
        ): Boolean = oldItem == newItem
    }
}