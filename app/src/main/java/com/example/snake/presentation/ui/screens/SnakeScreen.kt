package com.example.snake.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.snake.presentation.SnakeViewModel

@Composable
fun SnakeScreen(
    viewModel: SnakeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(8.dp, Color.Red, CircleShape)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, drag ->
                    Log.d("vishal", "SnakeScreen: horizontal $drag")
                    viewModel.moveSnake(true, drag < 0)
                }
            }
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, drag ->
                    Log.d("vishal", "SnakeScreen: vertical ${drag}")
                    viewModel.moveSnake(false, drag < 0)
                }
            }
    ) {
        Box(
            modifier = Modifier
                .offset(x = viewModel.xOffset.value, y = viewModel.yOffset.value)
                .size(10.dp)
                .background(Color.Green)
        ) {

        }
    }

}