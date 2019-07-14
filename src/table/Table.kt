package table
import playerInfo.*

class Table (var cardsOnTable: List<Card?> = listOf(Card(), Card(), Card(), Card(), Card())){
    var totalBet = 0
    init {
         cardsOnTable = listOf(Card(), Card(), Card(), Card(), Card())
    }

    fun openCard(cardNumber: Int){
        println("-------------------------")
        for (i in 0 until cardNumber) {
            print(cardsOnTable[i]?.rank)
            print(cardsOnTable[i]?.suit)
        }
        println("\n-------------------------")
    }

    fun defineWinner(player: Player, computer: Player) {

    }
}