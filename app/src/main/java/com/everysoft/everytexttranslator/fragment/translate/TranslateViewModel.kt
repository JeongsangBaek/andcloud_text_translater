package com.everysoft.everytexttranslator.fragment.translate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TranslateViewModel : ViewModel() {

    private val _translateInputText = MutableLiveData<String>()
    val translateInputText: LiveData<String>
        get() = _translateInputText

    private val _translateOutputText = MutableLiveData<String>()
    val translateOutputText: LiveData<String>
        get() = _translateOutputText

    init {
        Log.i("TranslateViewModel", "TranslateViewModel Created!")
        //TODO: Initialize translate api

    }

    fun doTranslate(text: String) {
        //TODO: use Firebase Translate API for translating text

        //prototype. reverse the string and return
        _translateOutputText.value = text.reversed()
    }

}
