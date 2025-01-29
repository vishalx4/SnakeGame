package com.example.snake.presentation.data

import kotlinx.serialization.Serializable


@Serializable
sealed class Screens(
    val route: String
) {
    @Serializable
    data object Snake : Screens("Snake")
    @Serializable
    data object Result : Screens("Result")
    @Serializable
    data object PlayPause : Screens("PlayPause")
}
