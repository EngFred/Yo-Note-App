package com.omongole.fred.notesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.omongole.fred.notesapp.ui.components.BottomBarComponent
import com.omongole.fred.notesapp.ui.navigation.AppNavigationGraph
import com.omongole.fred.notesapp.ui.screens.detail.DetailAssistedFactory
import com.omongole.fred.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var assistedFactory: DetailAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NotesAppTheme {
                NoteApp()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NoteApp() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomBarComponent( navHostController = navController )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                AppNavigationGraph(
                    navHostController = navController,
                    assistedFactory = assistedFactory,
                )
            }
        }
    }
}