package shawn.martin.circuit.navigation

import androidx.navigation.NavController
import shawn.martin.circuit.util.Constants.COMPONENT_SCREEN
import shawn.martin.circuit.util.Constants.HOME_SCREEN
import shawn.martin.circuit.util.Constants.LOGIN_SCREEN
import shawn.martin.circuit.util.Constants.SIGNUP_SCREEN
import shawn.martin.circuit.util.Constants.WELCOME_SCREEN

class Screens(navController: NavController) {

    // List of Screens, and how they are navigated to

    val welcome = {
        navController.navigate(route = WELCOME_SCREEN) {
            // when navigating to WelcomeScreen, pop previous until WelcomeScreen
            // inclusive = true => Previous instances of WelcomeScreen will be popped, allowing
            // only one instance of WelcomeScreen; one WelcomeScreen in the backstack
            popUpTo(WELCOME_SCREEN) { inclusive = true }
        }
    }
    val home = {
        navController.navigate(route = HOME_SCREEN) {
            // inclusive = true => after logging in, make HomeScreen the new 'default' screen
            // ensure no other screens are in the backstack when navigating to HomeScreen
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }
    val component = {
        // ComponentScreen is added to backstack; pushed.
        navController.navigate(route = COMPONENT_SCREEN)
    }
    val login = {
        // inclusive = false => pops everything out of the backstack until WelcomeScreen
        // backstack will be: WelcomeScreen,LoginScreen
        navController.navigate(route = LOGIN_SCREEN) {
            popUpTo(WELCOME_SCREEN) { inclusive = false }
        }
    }
    val signup = {
        navController.navigate(route = SIGNUP_SCREEN) {
            popUpTo(WELCOME_SCREEN) { inclusive = false }
        }
    }
}