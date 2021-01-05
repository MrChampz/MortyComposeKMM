package com.upco.playground.mortycomposekmm.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.upco.playground.mortycomposekmm.android.ui.characters.CharacterDetailView
import com.upco.playground.mortycomposekmm.android.ui.characters.CharacterListView

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object CharactersScreen: Screens("Characters", "Characters", Icons.Default.Person)
    object EpisodesScreen: Screens("Episodes", "Episodes", Icons.Default.Tv)
    object LocationsScreen: Screens("Locations", "Locations", Icons.Default.LocationOn)
    object CharacterDetailsScreen: Screens("CharacterDetails", "CharacterDetails")
    object EpisodeDetailsScreen: Screens("EpisodeDetails", "EpisodeDetails")
    object LocationDetailsScreen: Screens("LocationDetails", "LocationDetails")
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(Screens.CharactersScreen, Screens.EpisodesScreen, Screens.LocationsScreen)
    val bottomBar: @Composable () -> Unit = { MortyBottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.CharactersScreen.route) {
        composable(Screens.CharactersScreen.route) {
            CharacterListView(bottomBar) {
                navController.navigate("${ Screens.CharacterDetailsScreen.route }/${ it.id }")
            }
        }
        composable("${ Screens.CharacterDetailsScreen.route }/{id}") { backStackEntry ->
            CharacterDetailView(
                characterId = backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() }
            )
        }
        composable(Screens.EpisodesScreen.route) {
            CharacterListView(bottomBar) {
                navController.navigate("${ Screens.EpisodesScreen.route }/${ it.id }")
            }
        }
        composable("${ Screens.EpisodeDetailsScreen.route }/{id}") { backStackEntry ->
            CharacterDetailView(
                characterId = backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() }
            )
        }
        composable(Screens.LocationsScreen.route) {
            CharacterListView(bottomBar) {
                navController.navigate("${ Screens.LocationsScreen.route }/${ it.id }")
            }
        }
        composable("${ Screens.LocationDetailsScreen.route }/{id}") { backStackEntry ->
            CharacterDetailView(
                characterId = backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun MortyBottomNavigation(
    navController: NavHostController,
    items: List<Screens>
) {
    val currentRoute = currentRoute(navController)
    items.forEach { screen ->
        BottomNavigationItem(
            icon = { screen.icon?.let { Icon(screen.icon) } },
            label = { Text(screen.label) },
            selected = currentRoute == screen.route,
            onClick = {
                // This if check give us a "singleTop" behavior where we do not create a
                // second instance of the composable if we are already on that destination
                if (currentRoute != screen.route) {
                    navController.navigate(screen.route)
                }
            }
        )
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}