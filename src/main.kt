import playerInfo.*
import java.util.*
import table.*
import java.lang.Exception

fun main(){
    do{
        greetings()
        playGame()
    }while (playAgain())
}

fun playGame() {
    val player = Player()
    val computer = Computer()
    val table = Table()

    ////////////////First circle////////////////////
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)

    table.openCard(1)
    table.openCard(2)
    table.openCard(3)
    ////////////////////////////////////////////////
    ////////////////Second circle///////////////////
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)

    table.openCard(4)
    ////////////////////////////////////////////////
    /////////////////Fourth circle//////////////////
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)

    table.openCard(5)
    ////////////////////////////////////////////////
    /////////////////Fifth circle///////////////////
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)
    ////////////////////////////////////////////////
    table.defineWinner(player, computer)

    player.saveBalance()
    computer.saveBalance()
}

fun gameStats(player: Player, computer: Computer, table: Table) {
    println("""
        Your hand: ${player.hand.first.rank.getRankName()}${player.hand.first.suit} ${player.hand.second.rank.getRankName()}${player.hand.second.suit}
        Your balance is: ${player.balance} Computer balance is: ${computer.balance}
        Total bet is: ${table.totalBet} Computer's hand is ${computer.hand.first.rank.getRankName()}${computer.hand.first.suit} ${computer.hand.second.rank.getRankName()}${computer.hand.second.suit}
    """.trimIndent())
}

fun greetings() {
    println("""
        Hello! You are playing poker with computer.
    """.trimIndent())
}

fun playAgain(): Boolean {
    println("Do you want to play again?")
    val input = Scanner(System.`in`)
    return when (input.nextLine()){
        "Yes" -> true
        "No" -> false
        else -> throw Exception("Invalid input")
    }
}

