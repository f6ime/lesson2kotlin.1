package com.example.lesson2kotlin1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.lesson2kotlin1.R
import com.example.lesson2kotlin1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.charactersFragment,
            R.id.locationsFragment,
            R.id.episodeFragment //episodesFragment
        ).build()
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        binding.bottomNavigation.itemIconTintList = null
    }
}