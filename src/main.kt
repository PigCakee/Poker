import playerInfo.*
import java.util.*
import table.*
import java.lang.Exception

const val ZERO = '0'

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
    computer.balance = DEFAULT_COMPUTER_MONEY

    //First circle
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)
    table.openCard(1)
    table.openCard(2)
    table.openCard(3)
    //Second circle
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)
    table.openCard(4)
    //Fourth circle
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)
    table.openCard(5)
    //Fifth circle
    gameStats(player, computer, table)
    player.placeBet(table); computer.placeBet(table)

    table.defineWinner(player, computer)

    player.saveBalance()
}

fun gameStats(player: Player, computer: Computer, table: Table) {
    println("""
        Your hand: ${if (player.hand.first.rank == '1') "${player.hand.first.rank}0" else player.hand.first.rank}${player.hand.first.suit} ${if (player.hand.second.rank == '1') "${player.hand.second.rank}0" else player.hand.second.rank}${player.hand.second.suit}
        Your balance is: ${player.balance} Computer balance is: ${computer.balance}
        Total bet is: ${table.totalBet}
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

