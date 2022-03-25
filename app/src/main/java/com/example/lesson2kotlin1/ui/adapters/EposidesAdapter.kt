package com.example.lesson2kotlin1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import com.example.lesson2kotlin1.databinding.ItemEpisodeHolderBinding

class EposidesAdapter :
    PagingDataAdapter<RickAndMortyEpisode, EposidesAdapter.EpisodeViewHolder>(
        EpisodeComparator
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(
            ItemEpisodeHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: EposidesAdapter.EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    class EpisodeViewHolder(private val binding: ItemEpisodeHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: RickAndMortyEpisode) {
            binding.apply {
                tvEpisodeName.text = episode.name
                tvAirDate.text = episode.air_date
                tvEpisodeCode.text = episode.episode
            }
        }
    }

    object EpisodeComparator : DiffUtil.ItemCallback<RickAndMortyEpisode>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyEpisode,
            newItem: RickAndMortyEpisode
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RickAndMortyEpisode,
            newItem: RickAndMortyEpisode
        ): Boolean = oldItem == newItem
    }
}