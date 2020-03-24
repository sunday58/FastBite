package com.sundaydavid.fastBite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_az, R.id.navigation_category, R.id.navigation_search))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        setDestinationListener()

    }

    private fun setDestinationListener() {
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val dest = resources.getResourceName(destination.id)

            when (destination.id) {
                R.id.navigation_alphabet_detail -> {
                    hideCustomToolBar()
                    hideBottomNav()
                }
                else -> {
                    showCustomToolBar()
                    showBottomNav()
                }
            }
        }
    }

    private fun hideCustomToolBar() {
        supportActionBar?.hide()
    }

    private fun showCustomToolBar() {
        supportActionBar?.show()
    }
    
    private fun hideBottomNav(){
        nav_view.isVisible = false
    }
    
    private fun showBottomNav(){
        nav_view.isVisible = true
    }

}
