package com.example.snake.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestingUi(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize().border(1.dp, Color.White)
    ) {

        Box(
            modifier = Modifier.size(20.dp, 30.dp)
                .rotate(90f)
                .background(Color.Green)
        ) {

        }

    }

}

@Preview(showBackground = true, device = Devices.WEAR_OS_SMALL_ROUND)
@Composable
fun PreviewTesting(modifier: Modifier = Modifier) {
    TestingUi()
}