package com.rysanek.currencyconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rysanek.currencyconverter.R
import com.rysanek.currencyconverter.databinding.ActivityMainBinding
import com.rysanek.currencyconverter.utils.setUpSystemWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSystemWindow()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    private fun setupNavigation() {
        navController = findNavController(R.id.myNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.converterFragment -> {
        
                    navController.navigate(R.id.converterFragment)
                    binding.popView.background = ContextCompat.getDrawable(this, R.drawable.bg_nav_bar_converter)
                    true
                }
                R.id.graphFragment -> {
                    navController.navigate(R.id.graphFragment)
                    binding.popView.background = ContextCompat.getDrawable(this, R.drawable.bg_nav_bar_graph)
        
                    true
                }
                R.id.webFragment -> {
                    navController.navigate(R.id.webFragment)
                    binding.popView.background = ContextCompat.getDrawable(this, R.drawable.bg_nav_bar_web)
                    true
                }
                else                   -> {
                    true
                }
            }
        }
    }
}