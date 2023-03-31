package shawn.martin.circuit.navigation.destination_composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import shawn.martin.circuit.ui.screens.welcome.WelcomeScreen
import shawn.martin.circuit.util.Constants

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.welcomeComposable(
//    sharedViewModel: SharedViewModel,
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit,
) {
    composable(
        route = "welcome",
        // exiting this screen (going to Login/Signup), the screen will slide down.
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down,
                tween(Constants.ANIMATION_DURATION)
            )
        },

        ) { navBackStackEntry ->

        // DISPLAY UI of WELCOME SCREEN
        WelcomeScreen(
//            sharedViewModel = sharedViewModel,
            navigateToLogin = navigateToLogin,
            navigateToSignup = navigateToSignup,
        )
    }
}