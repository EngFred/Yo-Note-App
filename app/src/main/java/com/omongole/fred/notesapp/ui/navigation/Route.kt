package com.omongole.fred.notesapp.ui.navigation

sealed class Route( val destination: String ) {
    object HomeScreen : Route("home")
    object DetailScreen : Route("detail")
    object BookMarkScreen : Route("bookmark")
}
