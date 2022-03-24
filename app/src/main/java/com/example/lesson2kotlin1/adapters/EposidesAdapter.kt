package com.example.lesson2kotlin1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.adapters.diffutils.EpisodesDiffUtil
import com.example.lesson2kotlin1.data.remote.models.episode.RickAndMortyEpisode
import com.example.lesson2kotlin1.databinding.ItemEpisodeHolderBinding

class EposidesAdapter :
    ListAdapter<RickAndMortyEpisode, EposidesAdapter.EpisodeViewHolder>(EpisodesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(
            ItemEpisodeHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(getItem(position))
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
}