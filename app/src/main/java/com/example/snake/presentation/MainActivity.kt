/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.snake.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.unit.Density
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.snake.presentation.data.Screens
import com.example.snake.presentation.ui.screens.NavigationComponent
import com.example.snake.presentation.theme.SnakeTheme

class MainActivity : ComponentActivity() {

    val viewModel: SnakeViewModel by viewModels<SnakeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SnakeViewModel(
                        resources.displayMetrics.heightPixels,
                        resources.displayMetrics.widthPixels,
                        Density(this@MainActivity)
                    ) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            SnakeTheme {
                val navigationController = rememberNavController()
                NavigationComponent(
                    navigationController,
                    Screens.Snake.route,
                    viewModel
                )
            }
        }
    }
}