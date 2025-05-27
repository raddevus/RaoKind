package com.newlibre.raokind.ui.dailytask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newlibre.raokind.repo.KTaskRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class DailyTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val application: Application = application

    private val ktaskRepo = KTaskRepository(application.applicationContext)

    fun getAllKTasks(){
        viewModelScope.launch {
            Log.d("TEST", "got tasks!" ?: "Could not retrieve the KTasks.")
            var allTasks = ktaskRepo.getAllTasks()
            _text.postValue(allTasks[Random.nextInt(0, allTasks.count() -1)].description)
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is placeholder and should be very long to show that this could be a long thing is placeholder and should be very long to show that this could be a long thing"
    }
    val text: LiveData<String> = _text

    fun loadAllKTasks(){

    }
}