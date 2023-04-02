package shawn.martin.circuit.navigation.destination_composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import shawn.martin.circuit.ui.screens.login.LogInScreen
import shawn.martin.circuit.util.Constants.ANIMATION_DURATION
import shawn.martin.circuit.util.Constants.LOGIN_SCREEN
import shawn.martin.circuit.util.Constants.SIGNUP_SCREEN
import shawn.martin.circuit.util.Constants.WELCOME_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.logInComposable(
//    sharedViewModel: SharedViewModel,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable(
        route = LOGIN_SCREEN,
        enterTransition = {
            when {
                (initialState.destination.route == "welcome") ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        tween(ANIMATION_DURATION)
                    )
                (initialState.destination.route == "signup") ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right
                    )
                else -> null
            }
        },
        exitTransition = {
            when {
                (targetState.destination.route == SIGNUP_SCREEN) ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        tween(ANIMATION_DURATION)
                    )
                (targetState.destination.route == WELCOME_SCREEN) ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        tween(ANIMATION_DURATION)
                    )
                else -> slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    tween(ANIMATION_DURATION)
                )
            }
        }
    ) { navBackStackEntry ->

        // DISPLAY UI SCREEN
        LogInScreen(
//            sharedViewModel = sharedViewModel,
            navigateToSignUp = navigateToSignup,
            navigateToHome = navigateToHome
        )
    }
}