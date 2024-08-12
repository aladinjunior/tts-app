package com.aladinjunior.ttsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aladinjunior.ttsapp.data.repository.DefaultAppRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: DefaultAppRepository
) : ViewModel() {

    fun generateSpeech() = viewModelScope.launch {
        repository.generateSpeech()
    }

}