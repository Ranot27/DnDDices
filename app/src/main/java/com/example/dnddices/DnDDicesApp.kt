package com.example.dnddices

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Screens() {
    Main,
    Roll
}


@Composable
fun DndDicesApp(
    viewModel: DnDDicesViewModel = DnDDicesViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screens.Main.name
    ) {
        composable(route = Screens.Main.name) {
            DndDicesScreen(
                numberOfRolls = viewModel.uiState.numberOfRolls,
                onPlusClicked = { viewModel.onPlusClicked() },
                onMinusClicked = { viewModel.onMinusClicked() },
                navController = navController
            )
        }
        composable(route = "${Screens.Roll.name}/{max}") {back ->
            val max by rememberUpdatedState(back.arguments?.getString("max")?.toIntOrNull() ?: 0)
            var result by remember { mutableIntStateOf(0) }

            LaunchedEffect(max) {
                result += viewModel.rollDice(max)
            }

            RollScreen(
                result = result,
                onBackButtonCLicked = { onBackButtonCLicked(navController) }
            )
        }
    }
}

private fun onBackButtonCLicked(
    navController: NavHostController
){
    navController.popBackStack(Screens.Main.name, inclusive = false)
}


@Composable
fun RollScreen(
    result: Int,
    onBackButtonCLicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = { RollTopBar(onBackButtonCLicked = { onBackButtonCLicked() })},
        modifier = modifier.fillMaxSize()
    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = result.toString(),
                modifier = Modifier.fillMaxSize(),
                fontSize = 128.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun RollTopBar(
    onBackButtonCLicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
    ) {
        IconButton(onClick = { onBackButtonCLicked() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
}













