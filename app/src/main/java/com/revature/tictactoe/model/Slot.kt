package com.revature.tictactoe.model

data class Slot(
    var isTaken:Boolean = false,
    var sMark:String,
    var rowIndex:Int,
    var columnIndex:Int

)