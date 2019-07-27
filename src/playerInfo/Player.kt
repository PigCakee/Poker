package playerInfo

import table.Card
import table.Table
import java.io.File
import java.util.*
import kotlin.system.exitProcess

//const val DEFAULT_COMPUTER_MONEY = 7777

open class Player {
    open var balance = getSavedBalance()
    open var hand = Pair(first = Card(),second = Card())
    protected open fun getSavedBalance(): Int{
        val reader = File("D:/Poker/src/playerInfo/PlayerBalance.txt").bufferedReader()
        return reader.readLine().toInt()
    }
    open fun saveBalance(){
        File("D:/Poker/src/playerInfo/PlayerBalance.txt").writeText(this.balance.toString())
    }
    open fun placeBet(table: Table){
        println("Place your bet. For check place 0$. To pass type PASS")
        val input = Scanner(System.`in`)
        val amount = input.nextLine()
        when (amount){
            "0" -> println("Check.")
            "PASS" -> {
                println("Pass.")
                this.saveBalance()
                exitProcess(1)
            }
            else -> {
                println(amount.toInt())
                table.totalBet += amount.toInt()
                this.balance -= amount.toInt()
                println("Placed $amount$.")
            }
        }
    }
}

class Computer: Player(){
    override var balance = getSavedBalance()
    override var hand: Pair<Card, Card>
        get() = super.hand
        set(value) {}
    @Throws(Exception::class)
    override fun getSavedBalance(): Int{
        val reader = File("D:/Poker/src/playerInfo/ComputerBalance.txt").bufferedReader()
        return reader.readLine().toInt()
    }
    override fun saveBalance(){
        File("D:/Poker/src/playerInfo/ComputerBalance.txt").writeText(this.balance.toString())
    }
    override fun placeBet(table: Table){
        var bet = 0
        val score = table.checkPlayersScore(player = this).first
        val percentRate = when (score) {
            1 -> {
                (10..30).random()
            }
            2 -> {
                (10..40).random()
            }
            3 -> {
                (10..50).random()
            }
            4 -> {
                (10..60).random()
            }
            5 -> {
                (10..70).random()
            }
            6 -> {
                (10..80).random()
            }
            7 -> {
                (10..90).random()
            }
            8 -> {
                (10..100).random()
            }
            else -> {
                0
            }
        }

        bet = (this.balance * (percentRate * 0.01)).toInt()

        when ((1..3).random()){
            1 -> bet = 0
        }

        this.balance -= bet
        this.saveBalance()
        table.totalBet += bet
        println("Computer placed: $bet")
    }
}

fun isValidHands(player: Player, computer: Computer, table: Table): Boolean{
    val listOfCards =
        listOf<Card>(player.hand.first, player.hand.second,
            computer.hand.first, computer.hand.second,
            table.cardsOnTable.elementAt(0)!!, table.cardsOnTable.elementAt(1)!!,
            table.cardsOnTable.elementAt(2)!!, table.cardsOnTable.elementAt(3)!!,
            table.cardsOnTable.elementAt(4)!!
        )
    for (i in 0 until listOfCards.size - 1){
        for (j in i + 1 until listOfCards.size - 1){
            if (listOfCards[i].rank == listOfCards[j].rank && listOfCards[i].suit == listOfCards[j].suit) return false
        }
    }
    return true
}

fun genNewHands(player: Player, computer: Computer){
    player.hand = Pair(first = Card(),second = Card())
    computer.hand = Pair(first = Card(),second = Card())
}