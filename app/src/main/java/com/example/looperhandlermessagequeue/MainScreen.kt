package com.example.looperhandlermessagequeue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    name: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    viewModel: MyViewModel,
) {
    val msgState by viewModel.msgFlow.collectAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onButtonClick() }) {

        }
        Text(
            text = "Hello $msgState!",
            modifier = modifier
        )
    }
}