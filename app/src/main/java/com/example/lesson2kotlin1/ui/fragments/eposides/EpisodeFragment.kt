package com.example.lesson2kotlin1.ui.fragments.eposides

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.EpisodesAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.common.extension.sendData
import com.example.lesson2kotlin1.databinding.FragmentEpisodeBinding
import com.example.lesson2kotlin1.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodesViewModel>(R.layout.fragment_episode) {
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val episodeAdapter = EpisodesAdapter()
    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerview.apply {
            adapter = episodeAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addOnScrollListener(object :
                PaginationScrollListener(linearLayoutManager, { viewModel.fetchEpisodes() }) {
                override fun isLoading() = viewModel.isLoading
            })
        }
    }

    override fun setupObserver() {
        subscribeToEpisodes()
        subscribeToEpisodesLocale()
    }

    private fun subscribeToEpisodes() {
        viewModel.episodesState.observe(viewLifecycleOwner) {
            episodeAdapter.sendData(it.results)
        }
    }

    private fun subscribeToEpisodesLocale() {
        viewModel.episodesLocalState.observe(viewLifecycleOwner) {
            episodeAdapter.sendData(it)
        }
    }

    override fun setupRequest() {
        if (viewModel.episodesState.value == null && isOnline()) viewModel.fetchEpisodes()
        else viewModel.getEpisodes()
    }

    fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}