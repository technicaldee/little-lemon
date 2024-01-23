package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.ui.theme.Destination
import com.example.littlelemon.utils.SharedPreferencesUtils


@Composable
fun NavigationComposable(navController: NavHostController) {
    // Set up the NavHost with the provided NavController
    NavHost(
        navController = navController,
        startDestination = determineStartDestination()
    ) {
        // Define navigation routes for each destination
        composable(Destination.DestinationsImpl.home) {
            HomeScreen(navController)
        }
        composable(Destination.DestinationsImpl.profile) {
            ProfileScreen(navController)
        }
        composable(Destination.DestinationsImpl.onboarding) {
            Onboarding(navController)
        }
    }
}

@Composable
fun determineStartDestination(): String {
    val context = LocalContext.current
    val isUserDataAvailable = SharedPreferencesUtils.doesSharedPreferencesExist(context)

    return if (isUserDataAvailable) {
        Destination.DestinationsImpl.home
    } else {
        Destination.DestinationsImpl.onboarding
    }
}