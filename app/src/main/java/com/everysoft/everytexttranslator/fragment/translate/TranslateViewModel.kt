package com.everysoft.everytexttranslator.fragment.translate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import java.lang.Exception

class TranslateViewModel : ViewModel() {
    private val _translateInputText = MutableLiveData<String>()
    val translateInputText: LiveData<String>
        get() = _translateInputText

    private val _translateOutputText = MutableLiveData<String>()
    val translateOutputText: LiveData<String>
        get() = _translateOutputText

    init {
        Log.i("TranslateViewModel", "TranslateViewModel Created!")
    }

    fun doTranslate(sourceLang: String, targetLang: String, text: String) {
        try {
            val options = FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(FirebaseTranslateLanguage.languageForLanguageCode(sourceLang)!!)
                .setTargetLanguage(FirebaseTranslateLanguage.languageForLanguageCode(targetLang)!!)
                .build()
            val translator = FirebaseNaturalLanguage.getInstance().getTranslator(options)

            translator.downloadModelIfNeeded()
                .addOnSuccessListener {
                    translator.translate(text)
                        .addOnSuccessListener { translatedText ->
                            _translateOutputText.value = translatedText
                        }
                        .addOnFailureListener {exception ->
                            _translateOutputText.value = "! Failed to translate: ${exception.message}"

                        }
                }
                .addOnFailureListener {exception ->
                    _translateOutputText.value = "! Failed to download translation engine: ${exception.message}"

                }

        } catch (e: Exception) {
            _translateOutputText.value = "! Invalid translation: ${e.message}"
        }
    }
}
