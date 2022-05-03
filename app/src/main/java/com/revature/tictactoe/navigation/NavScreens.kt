package com.revature.tictactoe.navigation

sealed class NavScreen(val route:String){

    object MainMenuScreen:NavScreen("MainMenuScreen")
}