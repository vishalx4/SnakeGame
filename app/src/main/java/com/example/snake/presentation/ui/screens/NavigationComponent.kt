package com.example.snake.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.snake.presentation.SnakeViewModel
import com.example.snake.presentation.data.Screens

@Composable
fun NavigationComponent(
    navController: NavHostController,
    startDestination: String,
    viewModel: SnakeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Snake.route) {
            SnakeScreen(viewModel)
        }
    }

}