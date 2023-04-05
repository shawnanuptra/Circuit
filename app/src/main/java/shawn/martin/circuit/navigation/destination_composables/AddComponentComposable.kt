package shawn.martin.circuit.navigation.destination_composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import shawn.martin.circuit.ui.screens.component.AddComponentScreen
import shawn.martin.circuit.ui.screens.home.HomeScreen
import shawn.martin.circuit.util.Constants

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addComponentComposable(
    navigateToHome: () -> Unit
) {
    composable(
        route = Constants.ADD_COMPONENT_SCREEN,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                tween(Constants.ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                tween(Constants.ANIMATION_DURATION)
            )
        }
    ) {
            navBackStackEntry ->

        // Display UI
        AddComponentScreen(navigateToHome)
    }
}