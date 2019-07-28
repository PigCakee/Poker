import playerInfo.*
import java.util.*
import table.*
import java.lang.Exception
import kotlin.system.exitProcess

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

    while (!table.isValid()) { table.genNewTable() }
    while (!isValidHands(player = player, computer = computer, table = table)) genNewHands(player, computer)

    ////////////////First circle////////////////////
    gameStats(player = player, computer = computer, table = table)
    placeBets(player = player, computer = computer, table = table, turn = PLAYERS_TURN)

    table.openCard(1)
    table.openCard(2)
    table.openCard(3)
    ////////////////////////////////////////////////
    ////////////////Second circle///////////////////
    gameStats(player = player, computer = computer, table = table)
    placeBets(player = player, computer = computer, table = table, turn = COMPUTERS_TURN)

    table.openCard(4)
    ////////////////////////////////////////////////
    /////////////////Fourth circle//////////////////
    gameStats(player = player, computer = computer, table = table)
    placeBets(player = player, computer = computer, table = table, turn = PLAYERS_TURN)

    table.openCard(5)
    ////////////////////////////////////////////////
    /////////////////Fifth circle///////////////////
    gameStats(player = player, computer = computer, table = table)
    placeBets(player = player, computer = computer, table = table, turn = COMPUTERS_TURN)
    ////////////////////////////////////////////////
    table.defineWinner(player = player, computer = computer)

    player.saveBalance()
    computer.saveBalance()
}

fun gameStats(player: Player, computer: Computer, table: Table) {
    println("""
        Your hand: ${player.hand.first.rank.getRankName()}${player.hand.first.suit} ${player.hand.second.rank.getRankName()}${player.hand.second.suit}
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

fun placeBets(player: Player, computer: Computer, table: Table, turn: Int) {
    if (computer.balance == 0 || player.balance == 0) return
    println("Type Check, Call or PASS.")
    var playerBet: String = "1"
    var computerBet: String = "0"
    if (turn == PLAYERS_TURN) {
        while (playerBet != computerBet) {
            do {
                playerBet = player.placeBet()
                if (playerBet == "Call") playerBet = computerBet
                if (playerBet == "Check") playerBet = "0"
                if (playerBet == "PASS") exitProcess(1)
            } while (!(playerBet.toInt() >= computerBet.toInt() && playerBet.toInt() <= computer.balance && playerBet.toInt() <= player.balance))
            do {
                computerBet = computer.placeBet(table, player)
                if (computerBet.toInt() == CALL_NUMBER) computerBet = playerBet
                if (computerBet.toInt() == PASS_NUMBER) {
                    player.balance += table.totalBet
                    player.saveBalance()
                    computer.saveBalance()
                    println("Computer passed. You won ${table.totalBet}$.")
                    exitProcess(1)
                }
            } while (!(computerBet.toInt() >= playerBet.toInt() && computerBet.toInt() <= player.balance && computerBet.toInt() <= computer.balance))
            println("Computer placed $computerBet$")
        }
    }
    if (turn == COMPUTERS_TURN) {
        while (playerBet != computerBet) {
            do {
                computerBet = computer.placeBet(table, player)
                if (computerBet.toInt() == CALL_NUMBER) computerBet = playerBet
                if (computerBet.toInt() == PASS_NUMBER) {
                    player.balance += table.totalBet
                    player.saveBalance()
                    computer.saveBalance()
                    println("Computer passed. You won ${table.totalBet}$.")
                    exitProcess(1)
                }
            } while (!(computerBet.toInt() >= playerBet.toInt() && computerBet.toInt() <= player.balance && computerBet.toInt() <= computer.balance))
            println("Computer placed $computerBet$")
            do {
                playerBet = player.placeBet()
                if (playerBet == "Call") playerBet = computerBet
                if (playerBet == "Check") playerBet = "0"
                if (playerBet == "PASS") exitProcess(1)
            } while (!(playerBet.toInt() >= computerBet.toInt() && playerBet.toInt() <= computer.balance && playerBet.toInt() <= player.balance))
        }
    }
    table.totalBet += (computerBet.toInt() + playerBet.toInt())
    player.balance -= playerBet.toInt()
    computer.balance -= computerBet.toInt()
}

