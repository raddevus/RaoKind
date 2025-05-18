package com.newlibre.raokind.ui.dailytask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.newlibre.raokind.R
import com.newlibre.raokind.databinding.FragmentDailytaskBinding

class DailyTaskFragment : Fragment() {

    private var _binding: FragmentDailytaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dailyTaskViewModel =
            ViewModelProvider(this).get(DailyTaskViewModel::class.java)
            dailyTaskViewModel

        _binding = FragmentDailytaskBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val taskTextView: TextView = binding.taskTextView
        val newTaskButton: ImageButton = binding.newTaskButton
        val textView: TextView = binding.textDailytask

        dailyTaskViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        dailyTaskViewModel.text.observe(viewLifecycleOwner){
            taskTextView.text = it
        }

        newTaskButton.setOnClickListener{
            dailyTaskViewModel.getAllKTasks()
        }
        dailyTaskViewModel.loadAllKTasks()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}