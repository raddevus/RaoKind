package com.newlibre.raokind.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newlibre.raokind.repo.QuoteRepository
import kotlinx.coroutines.launch
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.newlibre.raokind.repo.QuoteResponse

class MainViewModel : ViewModel() {

    private val repository = QuoteRepository()
    var dailyQuote: String = "Loading..."

    fun loadQuote() {
        var gson = Gson()
        viewModelScope.launch {
            dailyQuote = repository.fetchDailyQuote() ?: "Could not retrieve the daily quote."
            var qr :QuoteResponse = gson.fromJson<QuoteResponse>(dailyQuote,
                object : TypeToken<QuoteResponse>(){})

            Log.d("TEST", "got it: " + qr.quote.content)
            Log.d("TEST", "called loadQuote - setting")
            Log.d("TEST", dailyQuote)
            _text.postValue(qr.quote.content + "\n\n~ " + qr.quote.fName + " " + qr.quote.lName)
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Main Fragment"
    }
    val text: LiveData<String> = _text
}