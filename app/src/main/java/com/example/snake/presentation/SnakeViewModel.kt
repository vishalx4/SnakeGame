package com.example.snake.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SnakeViewModel(
    val height: Int,
    val width: Int,
    private val density: Density
): ViewModel() {

    var motionJob: Job? = null
    var xOffset = mutableStateOf(0.dp)
    var yOffset = mutableStateOf(with(density) { (height/2).toDp() })

    init {
        moveSnake(true, false)
    }

    fun moveSnake(
        movingGestureState: Boolean,
        direction: Boolean
    ) {
        if (motionJob?.isActive == true) {
            motionJob?.cancel()
        }
        motionJob = viewModelScope.launch {
            with(density) {
                while (true) {
                    delay(100)
                    if (movingGestureState) {
                        xOffset.value = if (direction) {
                            val x = ((xOffset.value.toPx() - SPEED) % width)// back
                            if (x <= 0) width.toDp()
                            else x.toDp()
                        } else {
                            ((xOffset.value.toPx() + SPEED) % width).toDp() // forword
                        }
                    } else {
                        yOffset.value = if (direction) {
                            val y = ((yOffset.value.toPx() - SPEED)) // going up
                            if (y <= 0) height.toDp()
                            else y.toDp()
                        } else {
                            ((yOffset.value.toPx() + SPEED) % height).toDp() // down
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val SPEED = 10
    }

}