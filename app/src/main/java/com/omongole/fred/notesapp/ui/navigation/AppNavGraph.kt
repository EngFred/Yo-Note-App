package com.omongole.fred.notesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.omongole.fred.notesapp.ui.screens.bookmark.BookMarkScreen
import com.omongole.fred.notesapp.ui.screens.detail.DetailAssistedFactory
import com.omongole.fred.notesapp.ui.screens.detail.DetailScreen
import com.omongole.fred.notesapp.ui.screens.home.HomeScreen

@Composable
fun AppNavigationGraph(
    navHostController: NavHostController,
    assistedFactory: DetailAssistedFactory
) {
    NavHost( navController = navHostController , startDestination = Route.HomeScreen.destination ) {
        composable( route = Route.HomeScreen.destination ) {
            HomeScreen( navigateToDetailScreen = {
                navHostController.navigateToSingleTop( "${Route.DetailScreen.destination}?id=$it" )
            } )
        }
        composable( route = Route.BookMarkScreen.destination ) {
            BookMarkScreen()
        }
        composable(
            route = "${Route.DetailScreen.destination}?id={noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            val noteId = it.arguments?.getLong("noteId") ?: -1L
            DetailScreen(
                noteId = noteId,
                assistedFactory = assistedFactory,
                navigateUp = { navHostController.navigateUp() }
            )
        }
    }
}

fun NavHostController.navigateToSingleTop( destination: String)  {
    navigate(destination) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}