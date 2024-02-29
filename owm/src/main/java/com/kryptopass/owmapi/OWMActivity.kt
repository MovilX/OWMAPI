package com.kryptopass.owmapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kryptopass.common.nav.NavRoutes
import com.kryptopass.owmapi.ui.list.WeatherListScreen
import com.kryptopass.owmapi.ui.single.WeatherScreen
import com.kryptopass.owmapi.ui.theme.FirstMapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OWMActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstMapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Locations.route) {
        composable(route = NavRoutes.Locations.route) {
            WeatherListScreen(hiltViewModel(), navController)
        }
        composable(
            route = NavRoutes.Location.route,
            arguments = NavRoutes.Location.arguments
        ) {
            WeatherScreen(
                hiltViewModel(),
                NavRoutes.Location.fromEntry(it)
            )
        }
    }
}