package com.sundaydavid.fastBite

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.isVisible
import androidx.navigation.NavController

import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

         navController = findNavController(R.id.nav_host_fragment)
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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest = resources.getResourceName(destination.id)
            Log.d("Item destination", dest)

            when (destination.id) {
                R.id.navigation_alphabet_detail,
                R.id.navigation_alphabet_list,
                R.id.navigation_categoryDetail -> run {
                    hideCustomToolBar()
                    hideBottomNav()
                    return@run
                }
                R.id.navigation_search -> run {
                    hideCustomToolBar()
                    showBottomNav()
                    return@run
                }
                else -> {
                    showCustomToolBar()
                    showBottomNav()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination!!.id == R.id.navigation_az)
            showDialog()
        else if (navController.currentDestination!!.id == R.id.navigation_category ||
            navController.currentDestination!!.id == R.id.navigation_search)
            navController.navigate(R.id.navigation_az)
        else
            super.onBackPressed()
    }

    private fun showDialog() {
        val dialog = MaterialAlertDialogBuilder(this@MainActivity)
        dialog.setTitle("Exiting?")
        dialog.setIcon(R.drawable.ic_close_black_24dp)
        dialog.setMessage("Are you sure you want to exit?")
            .setPositiveButton(
                "YES"
            ) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                exitProcess(0)
            }
            .setNegativeButton(
                "NO"
            ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
        dialog.create().show()
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
