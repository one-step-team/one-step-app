package com.colddelight.onestep.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.colddelight.history.navigation.HistoryRoute
import com.colddelight.history.navigation.navigateToHistory
import com.colddelight.home.navigation.HomeRoute
import com.colddelight.home.navigation.navigateToHome
import com.colddelight.onestep.navigation.TopLevelDestination
import com.colddelight.onestep.navigation.TopLevelDestination.HOME
import com.colddelight.onestep.navigation.TopLevelDestination.HISTORY
import com.colddelight.onestep.navigation.TopLevelDestination.ROUTINE
import com.colddelight.routine.navigation.RoutineRoute
import com.colddelight.routine.navigation.navigateToRoutine
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberStepAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    shouldShowBottomBar: Boolean,
): StepAppState {
    return remember(
        navController,
        coroutineScope,
        shouldShowBottomBar
    ) {
        StepAppState(
            navController,
            shouldShowBottomBar
        )
    }
}
@Stable
class StepAppState(
    val navController: NavHostController,
    private var shouldShowBottomBar: Boolean,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HomeRoute.route -> HOME
            HistoryRoute.route -> HISTORY
            RoutineRoute.route -> ROUTINE
            else -> null
        }
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {

                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> {
                    navController.navigateToHome(topLevelNavOptions)
                    shouldShowBottomBar = true
                }

                HISTORY -> navController.navigateToHistory(topLevelNavOptions)
                ROUTINE -> navController.navigateToRoutine(topLevelNavOptions)
            }
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

}