package com.newlibre.raokind

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.newlibre.raokind.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggleButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        toggleButton = findViewById<ImageButton>(R.id.toggleButton)
        val textView: TextView = findViewById(R.id.textView)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_dailytask, R.id.navigation_history, R.id.navigation_profile
            )
        )
       toggleButton.setOnClickListener {
           toggleVisibility(navView)
       }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun toggleVisibility(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        val isExpanded = textView.visibility == View.VISIBLE
        textView.visibility = if (isExpanded) View.GONE else View.VISIBLE

        // Change arrow direction
        toggleButton.setImageResource(if (isExpanded) R.drawable.ic_arrow_right else R.drawable.ic_arrow_down)
    }
}