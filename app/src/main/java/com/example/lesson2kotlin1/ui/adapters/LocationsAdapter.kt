package com.example.lesson2kotlin1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.ui.adapters.diffutils.LocationsDiffUtil
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import com.example.lesson2kotlin1.databinding.ItemLocationHolderBinding
import com.example.lesson2kotlin1.models.episode.RickAndMortyEpisode

class LocationsAdapter :
    PagingDataAdapter<RickAndMortyLocation, LocationsAdapter.LocationsViewHolder>(
        LocationComparator
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder =
        LocationsViewHolder(
            ItemLocationHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: LocationsAdapter.LocationsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    class LocationsViewHolder(private val binding: ItemLocationHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(rickAndMortyLocation: RickAndMortyLocation) {
            binding.tvLocationName.text = rickAndMortyLocation.name
            binding.tvLocationType.text = rickAndMortyLocation.type
            binding.tvDimensionName.text = rickAndMortyLocation.dimension
        }
    }

    object LocationComparator : DiffUtil.ItemCallback<RickAndMortyLocation>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyLocation,
            newItem: RickAndMortyLocation
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RickAndMortyLocation,
            newItem: RickAndMortyLocation
        ): Boolean = oldItem == newItem
    }
}