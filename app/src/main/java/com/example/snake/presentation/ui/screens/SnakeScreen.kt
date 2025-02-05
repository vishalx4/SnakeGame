package com.example.snake.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.snake.presentation.SnakeViewModel

@Composable
fun SnakeScreen(
    viewModel: SnakeViewModel
) {
    val degree = remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp, Color.White, CircleShape)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, drag ->
                    degree.floatValue = 0f
                    viewModel.moveSnake(true, drag < 0)
                }
            }
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, drag ->
                    degree.floatValue = 90f
                    viewModel.moveSnake(false, drag < 0)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .offset(x = viewModel.xOffset.value, y = viewModel.yOffset.value)
                .rotate(degree.floatValue)
                .border(1.dp, Color.White)
        ) {
            repeat(viewModel.snakeSizeCount.intValue) {
                Box(
                    modifier = Modifier

                        .size(10.dp)
                        .clip(CircleShape)
                        .background(Color.Green)

                ) { }
                Spacer(Modifier.width(1.dp))
            }
        }

        Box(
            modifier = Modifier
                .offset(
                    x = viewModel.coordinates.value.first,
                    y = viewModel.coordinates.value.second,
                )
                .size(10.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )

    }

}