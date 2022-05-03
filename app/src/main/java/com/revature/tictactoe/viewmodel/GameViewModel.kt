package com.revature.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revature.tictactoe.model.Slot

class GameViewModel: ViewModel() {

    private val board: MutableLiveData<MutableList<MutableList<Slot>>> by lazy {
        MutableLiveData<MutableList<MutableList<Slot>>>()
    }

    var bGameFinished = false
    var sWinner = ""
    fun getBoard():LiveData<MutableList<MutableList<Slot>>>{
        return board
    }
    fun startGame(){
        bGameFinished = false
        sWinner = ""
        var rowIndex = 0
        var columnIndex = 0

        board.value = MutableList(3){
            rowIndex = 0

            MutableList(3){
                Slot(
                    sMark = " ",
                    rowIndex = rowIndex++,
                    columnIndex = columnIndex++ /3
                )
            }
        }
    }
    fun updateSlot(slot:Slot){

        val newList : MutableList<MutableList<Slot>> = board.value?.map { columns->
            val newColumn = columns.map { row->
                if (slot.columnIndex == row.columnIndex && slot.rowIndex == row.rowIndex){
                    row.sMark = slot.sMark
                    row.isTaken = true
                }
                row
            }
            newColumn
        } as MutableList<MutableList<Slot>>

        board.value?.removeAll {true}
        board.value = newList

        //check end game
        checkWinner()
    }

    private fun checkWinner(){

        //Check rows for winner
        board.value?.forEach { row->
            if( row[0].sMark == row[1].sMark
                && row[1].sMark == row[2].sMark
                && row[0].isTaken
            ){
                bGameFinished = true
                sWinner = row[0].sMark
            }
        }

        //check columns for winner
        (0..2).forEach { index->
            if(
                board.value?.get(0)!!.get(index).sMark == board.value?.get(1)!!.get(index).sMark
                && board.value?.get(1)!!.get(index).sMark == board.value?.get(2)!!.get(index).sMark
                && board.value?.get(0)!!.get(index).isTaken
            ){
                bGameFinished = true
                sWinner = board.value?.get(0)!!.get(index).sMark
            }
        }

        //Check diagonal for winner
        if (
            board.value?.get(0)!!.get(0).sMark == board.value?.get(1)!!.get(1).sMark
            && board.value?.get(1)!!.get(1).sMark == board.value?.get(2)!!.get(2).sMark
            && board.value?.get(0)!!.get(0).isTaken
        ){
            bGameFinished = true
            sWinner = board.value?.get(0)!!.get(0).sMark
        }
        if (
            board.value?.get(0)!!.get(2).sMark == board.value?.get(1)!!.get(1).sMark
            && board.value?.get(1)!!.get(1).sMark == board.value?.get(2)!!.get(0).sMark
            && board.value?.get(0)!!.get(2).isTaken
        ){
            bGameFinished = true
            sWinner = board.value?.get(0)!!.get(2).sMark
        }

        //if finished
//        bGameFinished = true
    }
}