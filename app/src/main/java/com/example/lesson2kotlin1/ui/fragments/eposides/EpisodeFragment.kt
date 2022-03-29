package com.example.lesson2kotlin1.ui.fragments.eposides

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.EposidesAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.databinding.FragmentEpisodeBinding
import com.example.lesson2kotlin1.ui.adapters.loader.LoadingLoaderStateAdapter
import com.example.lesson2kotlin1.ui.fragments.viewModels.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodesViewModel>(R.layout.fragment_episode) {
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val episodeAdapter = EposidesAdapter()
    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerview.adapter = episodeAdapter
        binding.apply {
            recyclerview.adapter = episodeAdapter.withLoadStateHeaderAndFooter(
                header = LoadingLoaderStateAdapter { episodeAdapter.retry() },
                footer = LoadingLoaderStateAdapter { episodeAdapter.retry() }
            )
        }
    }

    override fun setupObserver() {
        subscribeToEpisodes()
    }

    private fun subscribeToEpisodes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }
}