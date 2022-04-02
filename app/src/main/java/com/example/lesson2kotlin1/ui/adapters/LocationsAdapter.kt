package com.example.lesson2kotlin1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.base.diff_utils.BaseDiffUtils
import com.example.lesson2kotlin1.models.location.RickAndMortyLocation
import com.example.lesson2kotlin1.databinding.ItemLocationHolderBinding

class LocationsAdapter :
    ListAdapter<RickAndMortyLocation, LocationsAdapter.LocationsViewHolder>(BaseDiffUtils()) {


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
}