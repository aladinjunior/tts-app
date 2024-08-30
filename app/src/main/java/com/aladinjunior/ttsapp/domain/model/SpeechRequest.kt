package com.aladinjunior.ttsapp.domain.model

import com.google.gson.annotations.SerializedName

data class SpeechRequest(
    @SerializedName("Text")
    val text: String,
    @SerializedName("VoiceId")
    val voiceId: String,
    @SerializedName("Bitrate")
    val bitrate: String = "192k",
    @SerializedName("Speed")
    val speed: String = "0",
    @SerializedName("Pitch")
    val pitch: String = "1",
    @SerializedName("TimestampType")
    val timestampType: String
)