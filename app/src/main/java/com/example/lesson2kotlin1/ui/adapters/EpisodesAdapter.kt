package com.example.lesson2kotlin1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.base.diff_utils.BaseDiffUtils
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode
import com.example.lesson2kotlin1.databinding.ItemEpisodeHolderBinding

class EpisodesAdapter :
    ListAdapter<RickAndMortyEpisode, EpisodesAdapter.EpisodeViewHolder>(BaseDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(
            ItemEpisodeHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: EpisodesAdapter.EpisodeViewHolder, position: Int) {
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
}