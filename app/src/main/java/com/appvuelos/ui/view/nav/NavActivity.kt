package com.appvuelos.ui.view.nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.appvuelos.R
import com.appvuelos.databinding.ActivityNavBinding

class NavActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.navBar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                binding.navBar.menu.getItem(0).itemId -> navController.navigate(it.itemId)
                binding.navBar.menu.getItem(1).itemId -> navController.navigate(it.itemId)
                binding.navBar.menu.getItem(2).itemId -> navController.navigate(it.itemId)
            }
            true
        }
    }
}