package com.newlibre.raokind.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.newlibre.raokind.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.quoteTextView
        Log.d("TEST", "calling loadQuote...")

        mainViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        mainViewModel.loadQuote()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}