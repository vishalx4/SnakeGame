package com.example.snake.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SnakeViewModel(
    private val height: Int,
    private val width: Int,
    private val density: Density
): ViewModel() {

    init {
        moveSnake(true, false)
        //createObstacle()
    }

    var motionJob: Job? = null
    var xOffset = mutableStateOf(0.dp)
    var yOffset = mutableStateOf(with(density) { (height/2).toDp() })
    val coordinates: MutableState<Pair<Dp, Dp>> = mutableStateOf(Pair(0.dp, 0.dp)).also {
        var flag = true
        while (flag) {

            val x = with (density) {
                (0..width).random().toDp()
            }
            val y = with (density) {
                (0..width).random().toDp()
            }

            flag = isInsideTheCircle(x, y)
            Log.d("vishal", ": $x $y $height")
            it.value = Pair(x, y)
        }

    }
    val snakeSizeCount = mutableIntStateOf(2)


    private fun createObstacle() {
        val x = with (density) {
            (0..width).random().toDp()
        }
        val y = with (density) {
            (0..width).random().toDp()
        }

        if (!isInsideTheCircle(x, y)) {
            createObstacle()
        }
        Log.d("vishal", ": $x $y $height")
        coordinates.value = Pair(x, y)
    }

    private fun isInsideTheCircle(x: Dp, y: Dp): Boolean {

        val center = (height / 2f)

        val xCoordinate = with(density) {
            x.toPx().toDouble()
        }
        val yCoordinate = with(density) {
            y.toPx().toDouble()
        }

        val distance = Math.sqrt( (xCoordinate - center).sqr() + (yCoordinate - center).sqr() )

        return distance <= center
    }

    fun Double.sqr() = this * this

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
                            val x = ((xOffset.value.toPx() - SPEED) % width)
                            if (x <= 0) width.toDp()
                            else x.toDp()
                        } else {
                            ((xOffset.value.toPx() + SPEED) % width).toDp()
                        }
                    } else {
                        yOffset.value = if (direction) {
                            val y = ((yOffset.value.toPx() - SPEED))
                            if (y <= 0) height.toDp()
                            else y.toDp()
                        } else {
                            ((yOffset.value.toPx() + SPEED) % height).toDp()
                        }
                    }

                    Log.d("vishal", "moveSnake: ${xOffset.value} ${coordinates.value} ${coordinates.value.first + 10.dp}")

                    if (xOffset.value in coordinates.value.first ..(coordinates.value.first + 10.dp)) {
                        Log.d("vishal", "moveSnake: inside x")
                        snakeSizeCount.intValue++
                        createObstacle()
                    }

                    if (yOffset.value in coordinates.value.second .. (coordinates.value.second + 10.dp)) {
                        Log.d("vishal", "moveSnake: inside y")
                        snakeSizeCount.intValue++
                        createObstacle()
                    }

                }
            }
        }
    }

    companion object {
        const val SPEED = 10
    }

}