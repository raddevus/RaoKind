package com.newlibre.raokind.ui.dailytask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.newlibre.raokind.CurrentUserTask
import com.newlibre.raokind.R
import com.newlibre.raokind.UserTaskRepository
import com.newlibre.raokind.databinding.FragmentDailytaskBinding
import com.newlibre.raokind.repo.KTask
import com.newlibre.raokind.repo.KTaskRepository

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


        _binding = FragmentDailytaskBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val taskTextView: TextView = binding.taskTextView
        val newTaskButton: ImageButton = binding.newTaskButton
        val acceptTaskButton: ImageButton = binding.acceptTaskButton
        val textView: TextView = binding.textDailytask
        val taskCountMessage: TextView = binding.taskCountMessage

        dailyTaskViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        dailyTaskViewModel.text.observe(viewLifecycleOwner){
            taskTextView.text = it
        }

        newTaskButton.setOnClickListener{
            dailyTaskViewModel.getAllKTasks()
        }

        acceptTaskButton.setOnClickListener{
            CurrentUserTask.userTask?.let { ut -> UserTaskRepository.allUserTasks.add(0, ut) }
            Log.d("TEST", "CurrentUserTask - id: ${CurrentUserTask.userTask?.id}")
            val itemRemove : KTask? = UserTaskRepository.allUserTasks.find { it.id == CurrentUserTask.userTask?.id }
            if (itemRemove != null){
                taskCountMessage.text = "There are ${KTaskRepository.AllKTasks.allKTasks.count()} tasks available for selection."
                Log.d("TEST", "Removed item with id: ${itemRemove.id}")
                KTaskRepository.AllKTasks.allKTasks.remove(itemRemove)
                dailyTaskViewModel.saveKTaskJson(KTaskRepository.AllKTasks.allKTasks)
                dailyTaskViewModel.getAllKTasks()
            }
        }

        dailyTaskViewModel.loadAllKTasks()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}