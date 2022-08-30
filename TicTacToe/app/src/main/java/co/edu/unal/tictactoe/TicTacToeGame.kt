package co.edu.unal.tictactoe

import kotlin.random.Random


class TicTacToeGame {

    enum class DifficultyLevel {
        Easy, Harder, Expert
    }

    private var mDifficultyLevel: DifficultyLevel = DifficultyLevel.Easy

    fun getDifficultyLevel(): DifficultyLevel? {
        return mDifficultyLevel
    }

    fun setDifficultyLevel(difficultyLevel: DifficultyLevel) {
        mDifficultyLevel = difficultyLevel
    }

    private var mBoard = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    val BOARD_SIZE = 9

    val HUMAN_PLAYER = 'X'
    val COMPUTER_PLAYER = 'O'
    val OPEN_SPOT = ' '

    constructor() {

    }



    // Check for a winner.  Return
    //  0 if no winner or tie yet
    //  1 if it's a tie
    //  2 if X won
    //  3 if O won
    fun checkForWinner(): Int {

        // Check horizontal wins
        for (i in 0..6 step 3) {
            if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 1] == HUMAN_PLAYER && mBoard[i + 2] == HUMAN_PLAYER) {
                return 2
            }
            if (mBoard[i] == COMPUTER_PLAYER && mBoard[i + 1] == COMPUTER_PLAYER && mBoard[i + 2] == COMPUTER_PLAYER) {
                return 3
            }
        }
        // Check vertical wins
        for (i in 0..2) {
            if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 3] == HUMAN_PLAYER && mBoard[i + 6] == HUMAN_PLAYER) {
                return 2
            }
            if (mBoard[i] == COMPUTER_PLAYER && mBoard[i + 3] == COMPUTER_PLAYER && mBoard[i + 6] == COMPUTER_PLAYER) {
                return 3
            }
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[8] == HUMAN_PLAYER) ||
            (mBoard[2] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[6] == HUMAN_PLAYER)){
            return 2
        }

        if ((mBoard[0] == COMPUTER_PLAYER && mBoard[4] == COMPUTER_PLAYER && mBoard[8] == COMPUTER_PLAYER) ||
            (mBoard[2] == COMPUTER_PLAYER && mBoard[4] == COMPUTER_PLAYER && mBoard[6] == COMPUTER_PLAYER)){
            return 3
        }


        // Check for tie
        for (i in 0 until BOARD_SIZE) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER){
                return 0
            }
        }

        return 1
    }

    fun clearBoard() {
        mBoard = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    }

    fun setMove(player: Char, location: Int) {
        if (mBoard[location] == OPEN_SPOT) {
            mBoard[location] = player
        }
    }

    fun getComputerMove(): Int {
        var move: Int = -1

        if (mDifficultyLevel == DifficultyLevel.Easy){
            move = getRandomMove();
        }else if(mDifficultyLevel == DifficultyLevel.Harder){
            move = getWinningMove();
            if (move == -1){
                move = getRandomMove();
            }
        }else if (mDifficultyLevel == DifficultyLevel.Expert) {
            move = getWinningMove();
            if (move == -1) {
                move = getBlockingMove()
            }
            if (move == -1) {
                move = getRandomMove()
            }
        }
        return move
    }

    fun getWinningMove(): Int{
        // First see if there's a move O can make to win
        for (i in 0 until BOARD_SIZE) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                val curr = mBoard[i]
                mBoard[i] = COMPUTER_PLAYER
                if (checkForWinner() == 3) {
                    return i
                } else{
                    mBoard[i] = curr
                }
            }
        }
        return -1
    }


    fun getBlockingMove(): Int{
        // See if there's a move O can make to block X from winning
        for (i in 0 until BOARD_SIZE) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                val curr = mBoard[i] // Save the current number
                mBoard[i] = HUMAN_PLAYER
                if (checkForWinner() == 2) {
                    mBoard[i] = COMPUTER_PLAYER
                    return i
                } else mBoard[i] = curr
            }
        }
        return -1
    }

    fun getRandomMove(): Int{
        var move: Int;
        // Generate random move
        do {
            move = Random.nextInt(BOARD_SIZE)
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER)
        setMove(COMPUTER_PLAYER, move)
        return move;
    }




}