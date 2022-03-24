package com.example.lesson2kotlin1.ui.fragments.eposides

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.adapters.EposidesAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.common.resourse.Resource
import com.example.lesson2kotlin1.databinding.FragmentEpisodeBinding
import com.example.lesson2kotlin1.ui.fragments.viewModels.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodesViewModel>(R.layout.fragment_episode) {
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val adapter = EposidesAdapter()
    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerview.adapter = adapter
    }

    override fun setupObserver() {
        subscribeToEpisodes()
    }

    private fun subscribeToEpisodes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchEpisodes().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("GayPop", "Loading ")
                    }
                    is Resource.Error -> {
                        Log.e("GayPop", it.message.toString())
                    }
                    is Resource.Success -> {
                        it.data?.results?.let { it1 -> adapter.submitList(it1) }
                    }
                }
            }
        }
    }
}