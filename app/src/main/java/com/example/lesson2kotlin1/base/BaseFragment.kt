package com.example.lesson2kotlin1.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.lesson2kotlin1.base.view_model_base.BaseViewModel

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {
    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListeners()
        setupObserver()
        setupRequest()
    }

    open fun initialize() {}

    open fun setupViews() {}

    open fun setupListeners() {}

    open fun setupObserver() {}

    open fun setupRequest() {}
}