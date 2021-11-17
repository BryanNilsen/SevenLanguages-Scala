import scala.io.StdIn

class TicTacToeGame {
    var board = Array(
        "_", "_", "_",
        "_", "_", "_",
        "_", "_", "_"
    )

    var token = "X"

    def showBoard(){
        print("\u001b[2J")
        var topRow = board.take(3).mkString(" | ")
        var middleRow = board.slice(3,6).mkString(" | ")
        var bottomRow = board.takeRight(3).mkString(" | ")
        println("")
        for (element <- topRow) print(element)
        println("")
        println("----------")
        for (element <- middleRow) print(element)
        println("")
        println("----------")
        for (element <- bottomRow) print(element)
        println("")
        println("")
    }

    def makeMove(char: String, index: Int){
        if (board(index) == "_"){
            board(index) = char
            println(char + " played.")
            showBoard()
            changeToken()
            if(checkForWin(char)){
                println(char + " WON!!")
            } else {
                prompt()
            }
        } else {
            println("Position taken. Pick another square.")
            prompt()
        }
    }

    def changeToken(){
        if (token == "X") {
            token = "O"
        } else {
            token = "X"
        }
    }

    def prompt(){
        if (isBoardFull()){
            println("Board is full. It's a tie!")
        } else {
        println("Play " + token + " in which square?")
        var index = StdIn.readLine().toInt - 1
        makeMove(token, index)
        }
    }

    def isBoardFull(): Boolean = {
        return !board.contains("_")
    }

    def winArrays() : Array[Array[String]] ={
        var topRow = board.take(3)
        var middleRow = board.slice(3,6)
        var bottomRow = board.takeRight(3)
        var tlDiag = Array(board(0), board(4), board(8))
        var blDiag = Array(board(6), board(4), board(2))
        var leftCol = Array(board(0), board(3), board(6))
        var middleCol = Array(board(1), board(4), board(7))
        var rightCol = Array(board(2), board(5), board(8))

        return Array(topRow, middleRow, bottomRow, tlDiag, blDiag, leftCol, middleCol, rightCol)
    }

    def checkForWin(char: String) : Boolean = {
        var wins = winArrays()
        for (array <- wins){
            if (array.forall(el => el == char)){
                return true
            }
        }
        return false
    }
}

val game = new TicTacToeGame()
game.showBoard()
game.prompt()