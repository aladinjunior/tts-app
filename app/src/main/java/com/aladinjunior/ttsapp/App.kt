package com.aladinjunior.ttsapp

import AudioPlayerManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aladinjunior.ttsapp.playerspeech.AnimatedVolumeLevelBar
import com.aladinjunior.ttsapp.playerspeech.PlayerState
import com.aladinjunior.ttsapp.ui.theme.TTSAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aladinjunior.ttsapp.domain.model.SpeechRequest
import com.aladinjunior.ttsapp.util.TimestampType

@Composable
fun App(
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory())
) {
    AudioPlayerManager.buildMediaPlayerInstance()


    var text by remember { mutableStateOf("") }
    var playerState by remember { mutableStateOf(PlayerState.Idle) }
    var timestampType: String
    val speechUrl by viewModel.speechUrlFlow.collectAsState()



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text(text = "Insert a text to generate a speech")
            }
        )

        AnimatedVolumeLevelBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp),
            barWidth = 3.dp,
            gapWidth = 2.dp,
            isPlaying = playerState
        )

        Button(
            onClick = {

                if (text.isNotEmpty()) {
                    //sets the playerState as "playing"
                    //and calls the function that gets the mp3 speech
                    playerState = PlayerState.Playing
                    timestampType = isWordOrSentence(text)
                    viewModel.generateSpeech(
                        SpeechRequest(
                            text = text,
                            timestampType = timestampType
                        )
                    )
                }


            }
        ) {
            Text(text = "Generate Speech")
        }



        LaunchedEffect(key1 = playerState) {
            if (playerState == PlayerState.Playing) {
                AudioPlayerManager.prepareMedia(speechUrl) {
                    AudioPlayerManager.playMedia {
                        //sets the animated volume level bar as idle when audio finishes
                        playerState = PlayerState.Idle
                    }
                }
            }


        }


    }

}

private fun isWordOrSentence(
    text: String,
): String {
    return if (text.trim().contains(" ")) {
        TimestampType.SENTENCE
    } else {
        TimestampType.WORD
    }
}

@Preview
@Composable
private fun AppPreview() {
    TTSAppTheme {
        App()
    }
}