package com.example.lesson2kotlin1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.adapters.diffutils.LocationsDiffUtil
import com.example.lesson2kotlin1.data.remote.models.location.RickAndMortyLocation
import com.example.lesson2kotlin1.databinding.ItemLocationHolderBinding

class LocationsAdapter :
    ListAdapter<RickAndMortyLocation, LocationsAdapter.LocationsViewHolder>(LocationsDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder =
        LocationsViewHolder(
            ItemLocationHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.onBind(getItem(position))
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