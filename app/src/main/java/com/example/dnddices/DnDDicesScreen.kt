package com.example.dnddices

import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.dnddices.ui.theme.DnDDicesTheme


@Preview
@Composable
fun DndDicesScreenPreview() {
    DnDDicesTheme {
        DndDicesApp()
    }
}


@Composable
fun DndDicesScreen(
    numberOfRolls: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier,
        topBar = {
            DndDicesTopBar(
                numberOfRolls = numberOfRolls,
                onPlusClicked = {onPlusClicked()},
                onMinusClicked = {onMinusClicked()})
        }
    ) {paddingValues ->
        DicesGrid(
            navController = navController,
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
fun DicesGrid(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        items(dices) {dice ->
            DicesGridElement(
                dice = dice,
                onDiceClicked = { navController.navigate("${Screens.Roll.name}/${dice.max}") }
            )
        }
    }
}

@Composable
fun DicesGridElement(
    modifier: Modifier = Modifier,
    dice: DiceClass,
    onDiceClicked: () -> Unit,
){
    Box(modifier = modifier
        .fillMaxSize()
        .clickable { onDiceClicked() }) {
        Image(
            painter = painterResource(id = dice.image),
            contentDescription = null,
            modifier = Modifier.size(125.dp)
        )
    }
}

@Composable
fun DndDicesTopBar(
    numberOfRolls: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onMinusClicked) {
            Text(
                text = "-",
                fontSize = 18.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = numberOfRolls.toString(),
                fontSize = 18.sp
            )
            Text(
                text = "Number of rolls",
                fontSize = 18.sp
            )
        }
        Button(onClick = onPlusClicked) {
            Text(
                text = "+",
                fontSize = 18.sp
            )
        }
    }
}