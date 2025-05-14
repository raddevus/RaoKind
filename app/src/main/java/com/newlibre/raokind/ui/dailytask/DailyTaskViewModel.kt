package com.newlibre.raokind.ui.dailytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyTaskViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Daily Task Fragment"
    }
    val text: LiveData<String> = _text
}