package com.aladinjunior.ttsapp

import AudioPlayerManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aladinjunior.ttsapp.playerspeech.AnimatedVolumeLevelBar
import com.aladinjunior.ttsapp.playerspeech.PlayerState
import com.aladinjunior.ttsapp.ui.theme.TTSAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun App(
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory())
) {
    AudioPlayerManager.buildMediaPlayerInstance()


    var text by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("Insert a text to generate a speech") }
    var playerState by remember { mutableStateOf(PlayerState.Idle) }
    val coroutineScope = rememberCoroutineScope()


    val mediaPlayer = remember { MediaPlayer() }

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text(text = label)
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
                    playerState = PlayerState.Playing
//                    AudioPlayerManager.prepareMedia("https://unreal-expire-in-90-days.s3-us-west-2.amazonaws.com/7edf5a8b-aacb-47e6-b534-4963896710d9-0.mp3") {
//                        AudioPlayerManager.playMedia()
//                    }
                }


            }
        ) {
            Text(text = "Generate Speech")
        }

//        if (playerState == PlayerState.Playing) {
//            MediaPlayer.create(context, Uri.parse("https://unreal-expire-in-90-days.s3-us-west-2.amazonaws.com/7edf5a8b-aacb-47e6-b534-4963896710d9-0.mp3")).start()
//
//        }

        LaunchedEffect(key1 = playerState) {
            if (playerState == PlayerState.Playing) {
                AudioPlayerManager.prepareMedia("https://unreal-expire-in-90-days.s3-us-west-2.amazonaws.com/7edf5a8b-aacb-47e6-b534-4963896710d9-0.mp3") {
                    AudioPlayerManager.playMedia()
                }
            }


        }


    }

}

@Preview
@Composable
private fun AppPreview() {
    TTSAppTheme {
        App()
    }
}