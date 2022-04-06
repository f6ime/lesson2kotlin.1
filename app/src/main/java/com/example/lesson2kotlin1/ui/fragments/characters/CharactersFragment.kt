package com.example.lesson2kotlin1.ui.fragments.characters

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.ui.adapters.CharactersAdapter
import com.example.lesson2kotlin1.base.BaseFragment
import com.example.lesson2kotlin1.common.extension.sendData
import com.example.lesson2kotlin1.databinding.FragmentCharactersBinding
import com.example.lesson2kotlin1.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {
    override val binding: FragmentCharactersBinding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharactersAdapter(this::onItemClick)
    override fun setupViews() {
        setupAdapter()
    }


    private fun setupAdapter() {
        binding.recyclerview.apply {
            adapter = characterAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addOnScrollListener(object :
                PaginationScrollListener(linearLayoutManager, { viewModel.fetchCharacters() }) {
                override fun isLoading() = viewModel.isLoading
            })
        }
    }

    override fun setupRequest() {
        if (viewModel.characterState.value == null && isOnline()) viewModel.fetchCharacters()
        else viewModel.getCharacters()
    }


    override fun setupObserver() {
        subscribeToCharacters()
        subscribeToCharactersLocale()
    }

    private fun subscribeToCharacters() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            characterAdapter.sendData(it.results)
        }
    }

    private fun subscribeToCharactersLocale() {
        viewModel.characterLocalState.observe(viewLifecycleOwner) {
            characterAdapter.sendData(it)
        }
    }


    fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }


    private fun onItemClick(id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionSwitchingToADetailedFragment(id)
        )
    }

}