package shawn.martin.circuit.navigation.destination_composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import shawn.martin.circuit.ui.screens.component.EditComponentScreen
import shawn.martin.circuit.util.Constants
import shawn.martin.circuit.util.Constants.COMPONENT_DESC
import shawn.martin.circuit.util.Constants.COMPONENT_ID
import shawn.martin.circuit.util.Constants.COMPONENT_NAME
import shawn.martin.circuit.util.Constants.COMPONENT_PRICE
import shawn.martin.circuit.util.Constants.COMPONENT_PURCHASED
import shawn.martin.circuit.util.Constants.EDIT_COMPONENT_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.editComponentComposable(
    navigateToHome: () -> Unit
) {
    composable(
        route = "$EDIT_COMPONENT_SCREEN/{$COMPONENT_ID}",
        arguments = listOf(
            navArgument(COMPONENT_ID) { type = NavType.StringType },
        ),
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
    ) { navBackStackEntry ->

        // Display UI
        EditComponentScreen(
            navigateToHome,
            navBackStackEntry.arguments!!.getString(COMPONENT_ID)!!,
            )
    }
}