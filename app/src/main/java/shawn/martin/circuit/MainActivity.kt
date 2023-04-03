 package shawn.martin.circuit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import shawn.martin.circuit.navigation.SetUpNavigation
import shawn.martin.circuit.ui.theme.CircuitTheme

 @ExperimentalAnimationApi

 class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            CircuitTheme {
                navController = rememberAnimatedNavController()
                SetUpNavigation(navController = navController)

            }
        }
    }
}