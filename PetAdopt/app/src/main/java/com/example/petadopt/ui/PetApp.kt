package com.example.petadopt.ui

import AdoptScreen
import DetailScreen
import ListScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object List

    @Serializable
    data class  Detail(
        val petId: Int
    )

    @Serializable
    data class Adopt(
        val petId: Int
    )
}

@Composable
fun PetApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List
    ) {
        composable<Routes.List> {
            ListScreen(
                onImageClick = { pet ->
                    navController.navigate(
                        Routes.Detail(pet.id)
                    )
                }
            )
        }
        composable<Routes.Detail> { backstackEntry ->
            val details: Routes.Detail = backstackEntry.toRoute()

            DetailScreen(
                petID = details.petId,
                onAdoptClick = {
                    navController.navigate(
                        Routes.Adopt(details.petId)
                    )
                },
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Adopt> { backstackEntry ->
            val adopt: Routes.Adopt = backstackEntry.toRoute()

            AdoptScreen(
                petID = adopt.petId,
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetAppBar(
    title: String,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    onUpClick: () -> Unit

) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onUpClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            }
        }
    )
}