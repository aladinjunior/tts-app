package com.aladinjunior.ttsapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.aladinjunior.ttsapp.ui.theme.TTSAppTheme

@Composable
fun App() {

    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var label  by remember { mutableStateOf("Insert a text to generate a speech")}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = text,
            onValueChange = {
                text = it
                isError = text.isBlank()
            },
            label = {
                Text(text = label)
            }
        )

        Button(onClick = {
            if (isError) {
                label = "Field can't be empty"
            }
        }
        ) {
            Text(text = "Generate Speech")
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