package com.aladinjunior.ttsapp.network

import com.aladinjunior.ttsapp.BuildConfig
import com.aladinjunior.ttsapp.domain.model.SpeechRequest
import com.aladinjunior.ttsapp.domain.model.SpeechResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL = BuildConfig.BASE_URL
private const val API_KEY = BuildConfig.API_KEY
private interface RetrofitApiService {
    @POST("speech")
    suspend fun generateSpeech(
        @Body request: SpeechRequest,
        @Header("Authorization") apiKey: String = "Bearer $API_KEY",
    ): SpeechResponse
}


internal class RetrofitNetworkDataSource : AppNetworkDataSource {

    private val api: RetrofitApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApiService::class.java)
    }

    override suspend fun generateSpeech(request: SpeechRequest): SpeechResponse {
        return api.generateSpeech(request)
    }

}
