package com.revature.tictactoe.view

import android.content.Context
import android.view.WindowManager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.getSystemService
import androidx.navigation.NavController
import com.revature.tictactoe.R
import com.revature.tictactoe.viewmodel.GameViewModel

@Composable
fun MainMenuScreen(navController: NavController){

    val state = rememberLazyListState()
    val context = LocalContext.current

    val gameVM = GameViewModel()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(context.getString(R.string.app_name))},
                actions = {
                    IconButton(
                        onClick = {
                            gameVM.startGame()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Reload Game"
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            item{

                Spacer(modifier = Modifier.size(40.dp))

                DisplayBoard(gameVM)

            }
        }
    }
}
@Composable
fun DisplayBoard(gameVM:GameViewModel){

    val board = gameVM.getBoard().observeAsState(listOf())
    val playerXTurn = rememberSaveable{ mutableStateOf(true)}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        board.value.forEach { rows ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Center
            ) {
                rows.forEach { slot->
                    Column {
                        Card(
                            modifier = Modifier.border(2.dp,Color.Black)
                                .height(100.dp)
                                .width(100.dp)
                                .clickable {
                                   if (!slot.isTaken && !gameVM.bGameFinished){
                                       slot.isTaken = true
                                       slot.sMark = if (playerXTurn.value) "X" else "O"

                                       gameVM.updateSlot(slot)

                                       playerXTurn.value = !playerXTurn.value
                                   }
                                },
                        ) {
//                            slot.sMark = "X"
                            Text(
                                text = slot.sMark,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                fontSize = 50.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
        }

        if(board.value.isNotEmpty()) {

            Spacer(Modifier.size(10.dp))

            val sPlayerTurn =
                if(!gameVM.bGameFinished) {
                    if (playerXTurn.value) "Player X's turn" else "Player O's turn"
                } else{
                    "The Winner is: ${gameVM.sWinner}!"

                }
            Text(
                text = sPlayerTurn,
                fontSize = 20.sp
            )
        }
    }

}