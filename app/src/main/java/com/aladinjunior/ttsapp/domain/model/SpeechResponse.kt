package com.aladinjunior.ttsapp.domain.model

import com.google.gson.annotations.SerializedName

data class SpeechResponse(
    @SerializedName("CreationTime")
    val creationTime: String,
    @SerializedName("OutputUri")
    val speechUri: String,
    @SerializedName("RequestCharacters")
    val requestCharacters: Int,
    @SerializedName("TaskId")
    val taskId: String,
    @SerializedName("TaskStatus")
    val taskStatus: String,
    @SerializedName("TimestampsUri")
    val timeStampUri: String,
    @SerializedName("VoiceId")
    val voiceId: String,
)
