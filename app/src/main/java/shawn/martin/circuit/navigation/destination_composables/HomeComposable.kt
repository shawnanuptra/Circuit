package shawn.martin.circuit.navigation.destination_composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import shawn.martin.circuit.ui.screens.home.HomeScreen
import shawn.martin.circuit.util.Constants.ANIMATION_DURATION
import shawn.martin.circuit.util.Constants.HOME_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeComposable(
    // sharedViewModel: SharedViewModel,
    navigateToWelcome: () -> Unit,
    navigateToAddComponent: () -> Unit,
    navigateToEditComponent: (String) -> Unit,
) {
    composable(
        route = HOME_SCREEN,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                tween(ANIMATION_DURATION)
            )
        }
    ) {
        navBackStackEntry ->

        // Display UI
        HomeScreen(navigateToWelcome, navigateToAddComponent, navigateToEditComponent)
    }
}