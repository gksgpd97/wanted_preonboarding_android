package com.example.wantedpreonboardingandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wantedpreonboardingandroid.R
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomnavigaionviewMain

        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_main_navigationhost) as NavHostFragment).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_topnews, R.id.navigation_categories, R.id.navigation_saved
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun changeToNewsDetailFragment(fragment: Fragment, article: Article, before: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main_navigationhost, fragment.apply {
                arguments = Bundle().apply {
                    putSerializable("article", article)
                    putString("before", before)
                }
            })
            addToBackStack(null)
            Log.d("MainActivity", "newdetailfragment")
        }.commit()
    }

    fun changeToCategoryNewsFragment(fragment: Fragment, category: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main_navigationhost, fragment.apply {
                arguments = Bundle().apply {
                    putString("category", category)
                }
            })
            addToBackStack(null)
            Log.d("MainActivity", "topnewsfragment")
        }.commit()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}