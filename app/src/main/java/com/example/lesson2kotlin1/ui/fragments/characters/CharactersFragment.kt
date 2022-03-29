package com.example.lesson2kotlin1.ui.fragments.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.CharactersAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.databinding.FragmentCharactersBinding
import com.example.lesson2kotlin1.ui.adapters.loader.LoadingLoaderStateAdapter
import com.example.lesson2kotlin1.ui.fragments.viewModels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()

    private val characterAdapter = CharactersAdapter(this::onItemClick)
    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerview.adapter = characterAdapter
        binding.apply {
            recyclerview.adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = LoadingLoaderStateAdapter { characterAdapter.retry() },
                footer = LoadingLoaderStateAdapter { characterAdapter.retry() }
            )
        }
    }

    override fun setupObserver() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        lifecycleScope.launch{
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionSwitchingToADetailedFragment(id)
        )
    }
}