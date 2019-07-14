package playerInfo

import table.Card
import table.Table
import java.io.File
import java.util.*
import kotlin.system.exitProcess

const val DEFAULT_COMPUTER_MONEY = 1000

open class Player {
    open var balance = getSavedBalance()
    open var hand = Pair(first = Card(),second = Card())
    private fun getSavedBalance(): Int{
        val reader = File("D:/Poker/src/playerInfo/PlayerBalance.txt").bufferedReader()
        return reader.readLine().toInt()
    }
    fun saveBalance(){
        File("D:/Poker/src/playerInfo/PlayerBalance.txt").writeText(balance.toString())
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
    override var balance: Int
        get() = DEFAULT_COMPUTER_MONEY
        set(value) {}
    override var hand: Pair<Card, Card>
        get() = super.hand
        set(value) {}
    override fun placeBet(table: Table){
        println("Computer placed: ")
    }
}