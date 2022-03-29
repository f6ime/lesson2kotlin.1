package com.example.lesson2kotlin1.ui.adapters.loader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2kotlin1.databinding.LoadingLoadStateBinding
import com.example.lesson2kotlin1.ui.adapters.loader.LoadingLoaderStateAdapter.*

class LoadingLoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadingLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.OnBind(loadState)
    }

    class LoadStateViewHolder(private val binding: LoadingLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun OnBind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }
}