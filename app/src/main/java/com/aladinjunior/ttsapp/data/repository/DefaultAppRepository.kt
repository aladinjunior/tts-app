package com.aladinjunior.ttsapp.data.repository

import com.aladinjunior.ttsapp.domain.model.SpeechRequest
import com.aladinjunior.ttsapp.domain.model.SpeechResponse
import com.aladinjunior.ttsapp.network.AppNetworkDataSource

class DefaultAppRepository(
    private val dataSource: AppNetworkDataSource
) {

    suspend fun generateSpeech(request: SpeechRequest) : SpeechResponse {
        return dataSource.generateSpeech(request)
    }

}