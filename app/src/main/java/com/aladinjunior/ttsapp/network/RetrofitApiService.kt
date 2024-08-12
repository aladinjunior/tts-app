package com.aladinjunior.ttsapp.network

import com.aladinjunior.ttsapp.BuildConfig
import com.aladinjunior.ttsapp.domain.model.SpeechResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private interface RetrofitApiService {
    @GET("speech")
    suspend fun generateSpeech(): SpeechResponse
}

private const val BASE_URL = BuildConfig.BASE_URL

internal class RetrofitNetworkDataSource : AppNetworkDataSource {

    private val api: RetrofitApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApiService::class.java)
    }


    override suspend fun generateSpeech(): SpeechResponse {
        return api.generateSpeech()
    }

}
