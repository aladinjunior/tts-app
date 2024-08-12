package com.aladinjunior.ttsapp.network

import com.aladinjunior.ttsapp.domain.model.SpeechResponse

interface AppNetworkDataSource {

    suspend fun generateSpeech() : SpeechResponse
}