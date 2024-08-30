package com.aladinjunior.ttsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aladinjunior.ttsapp.data.repository.DefaultAppRepository
import com.aladinjunior.ttsapp.domain.model.SpeechRequest
import com.aladinjunior.ttsapp.network.RetrofitNetworkDataSource
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(
    private val repository: DefaultAppRepository
) : ViewModel() {

    fun generateSpeech(request: SpeechRequest) = viewModelScope.launch {
        repository.generateSpeech(request)
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(DefaultAppRepository(RetrofitNetworkDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}