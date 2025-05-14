package com.newlibre.raokind

import android.os.Bundle
import android.view.View
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
    lateinit var infoToggleButton: ImageButton
    lateinit var explainToggleButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        infoToggleButton = findViewById<ImageButton>(R.id.infoToggleButton)
        explainToggleButton = findViewById<ImageButton>(R.id.explainToggleButton)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_dailytask, R.id.navigation_history, R.id.navigation_profile
            )
        )
       infoToggleButton.setOnClickListener {
           toggleVisibility(navView)
       }
        explainToggleButton.setOnClickListener{
            toggleExplainVisibility(navView)
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun toggleVisibility(view: View) {
        val textView = findViewById<TextView>(R.id.infoTextView)
        val isExpanded = textView.visibility == View.VISIBLE
        textView.visibility = if (isExpanded) View.GONE else View.VISIBLE

        // Change arrow direction
        infoToggleButton.setImageResource(if (isExpanded) R.drawable.ic_arrow_right else R.drawable.ic_arrow_down)
    }

    fun toggleExplainVisibility(view: View) {
        val textView = findViewById<TextView>(R.id.explainTextView)
        val isExpanded = textView.visibility == View.VISIBLE
        textView.visibility = if (isExpanded) View.GONE else View.VISIBLE

        // Change arrow direction
        explainToggleButton.setImageResource(if (isExpanded) R.drawable.ic_arrow_right else R.drawable.ic_arrow_down)
    }

}