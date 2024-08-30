package com.aladinjunior.ttsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aladinjunior.ttsapp.data.repository.DefaultAppRepository
import com.aladinjunior.ttsapp.domain.model.SpeechRequest
import com.aladinjunior.ttsapp.network.RetrofitNetworkDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(
    private val repository: DefaultAppRepository
) : ViewModel() {

    private val _speechUrlFlow = MutableStateFlow("")
    val speechUrlFlow = _speechUrlFlow.asStateFlow()

    fun generateSpeech(request: SpeechRequest) = viewModelScope.launch {
        _speechUrlFlow.value = repository.generateSpeech(request).speechUrl
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