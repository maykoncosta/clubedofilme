package com.example.clubedofilme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.clubedofilme.R
import com.example.clubedofilme.databinding.ActivityHomeBinding
import com.example.clubedofilme.fragments.GroupFragment
import com.example.clubedofilme.fragments.HomeFragment
import com.example.clubedofilme.fragments.UserFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        loadFragment(HomeFragment())
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_search -> {
                    // Load search fragment or activity
                    // loadFragment(SearchFragment())
                    true
                }
                R.id.navigation_group -> {
                    loadFragment(GroupFragment())
                    true
                }
                R.id.navigation_user -> {
                    loadFragment(UserFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
