package com.example.todos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val windowInsetsController =
//            WindowCompat.getInsetsController(window, window.decorView)
//        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        // NavigationRail setup
        val navigationRailView: NavigationRailView = findViewById(R.id.nav_rail)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(navigationRailView, navController)

        // Handle NavigationRailButtons
        navigationRailView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.recipes_button -> {
                    navController.navigate(R.id.nav_recipe)
                    true
                }

                R.id.ingredients_button -> {
                    navController.navigate(R.id.nav_ingredient)
                    true
                }

                R.id.diary_button -> {
                    navController.navigate(R.id.nav_diary)
                    true
                }

                R.id.category_button -> {
                    navController.navigate(R.id.nav_category)
                    true
                }

                else -> false

            }
        }
    }

}