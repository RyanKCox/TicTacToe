package com.revature.tictactoe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revature.tictactoe.view.MainMenuScreen

@Composable
fun StartNav(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavScreen.MainMenuScreen.route
    ){
        //Main Menu Screen
        composable(NavScreen.MainMenuScreen.route){
            MainMenuScreen(navController = navController)
        }
    }
}