package shawn.martin.circuit.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import shawn.martin.circuit.navigation.destination_composables.logInComposable
import shawn.martin.circuit.navigation.destination_composables.signUpComposable
import shawn.martin.circuit.navigation.destination_composables.welcomeComposable
import shawn.martin.circuit.util.Constants.WELCOME_SCREEN

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetUpNavigation(
    navController: NavHostController
) {

    val screen = remember(navController) {
        Screens(navController)
    }

    // AnimatedNavHost
    AnimatedNavHost(navController = navController, startDestination = WELCOME_SCREEN) {
        // put all screen composables here
        welcomeComposable(
            navigateToLogin = screen.login,
            navigateToSignup = screen.signup
        )

        logInComposable(
            navigateToSignup = screen.signup,
            navigateToHome = screen.home
        )

        signUpComposable(
            navigateToHome = screen.home,
            navigateToLogIn = screen.login
        )

    }
}