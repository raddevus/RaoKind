package com.newlibre.raokind.ui.dailytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyTaskViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is placeholder and should be very long to show that this could be a long thing is placeholder and should be very long to show that this could be a long thing"
    }
    val text: LiveData<String> = _text
}