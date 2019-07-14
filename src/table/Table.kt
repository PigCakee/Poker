package table
import playerInfo.*

val CARDS = listOf('2', '3', '4', '5', '6', '7', '8', '9', '1', 'J', 'Q', 'K', 'A')

class Table (var cardsOnTable: List<Card?> = listOf(Card(), Card(), Card(), Card(), Card())){
    var totalBet = 0
    init {
         cardsOnTable = listOf(Card(), Card(), Card(), Card(), Card())
    }

    fun openCard(cardNumber: Int){
        println("-------------------------")
        for (i in 0 until cardNumber) {
            print(if (cardsOnTable[i]?.rank == '1') "${cardsOnTable[i]?.rank}0" else cardsOnTable[i]?.rank)
            print(cardsOnTable[i]?.suit)
            print(" ")
        }
        println("\n-------------------------")
    }

    fun defineWinner(player: Player, computer: Computer) {

    }

    fun <T> isPair(player: T): Pair<Boolean,Char?>{
        if (player is Player){
            if (player.hand.first.rank == player.hand.second.rank) return Pair(true, player.hand.first.rank)
            for (i in 0 until this.cardsOnTable.size){
                if (player.hand.first.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.first.rank)
                if (player.hand.second.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.second.rank)
            }
        }
        if (player is Computer){
            if (player.hand.first.rank == player.hand.second.rank) return Pair(true, player.hand.first.rank)
            for (i in 0 until this.cardsOnTable.size){
                if (player.hand.first.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.first.rank)
                if (player.hand.second.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.second.rank)
            }
        }
        return Pair(false, null)
    }

    fun <T> isThreeOfAKind(player: T): Pair<Boolean,Char?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank,
                player.hand.second.rank,
                this.cardsOnTable[0]!!.rank,
                this.cardsOnTable[1]!!.rank,
                this.cardsOnTable[2]!!.rank,
                this.cardsOnTable[3]!!.rank,
                this.cardsOnTable[4]!!.rank)
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 3) return Pair(true, this.cardsOnTable[i]?.rank)
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank,
                player.hand.second.rank,
                this.cardsOnTable[0]!!.rank,
                this.cardsOnTable[1]!!.rank,
                this.cardsOnTable[2]!!.rank,
                this.cardsOnTable[3]!!.rank,
                this.cardsOnTable[4]!!.rank)
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 3) return Pair(true, this.cardsOnTable[i]?.rank)
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        return Pair(false, null)
    }

    fun <T> isFourOfAKind(player: T): Pair<Boolean,Char?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank,
                player.hand.second.rank,
                this.cardsOnTable[0]!!.rank,
                this.cardsOnTable[1]!!.rank,
                this.cardsOnTable[2]!!.rank,
                this.cardsOnTable[3]!!.rank,
                this.cardsOnTable[4]!!.rank)
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 4) return Pair(true, this.cardsOnTable[i]?.rank)
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank,
                player.hand.second.rank,
                this.cardsOnTable[0]!!.rank,
                this.cardsOnTable[1]!!.rank,
                this.cardsOnTable[2]!!.rank,
                this.cardsOnTable[3]!!.rank,
                this.cardsOnTable[4]!!.rank)
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 4) return Pair(true, this.cardsOnTable[i]?.rank)
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        return Pair(false, null)
    }

    fun <T> highestCardInHand(player: T): Int{
        var highestCard = 0
        if (player is Player){
            for (i in 0 until CARDS.size){
                if (player.hand.first.rank == CARDS[i]) highestCard = i
                if (player.hand.second.rank == CARDS[i] && highestCard < i) highestCard = i
            }
        }
        if (player is Computer){
            for (i in 0 until CARDS.size){
                if (player.hand.first.rank == CARDS[i]) highestCard = i
                if (player.hand.second.rank == CARDS[i] && highestCard < i) highestCard = i
            }
        }
        return highestCard
    }

    fun <T> isTwoPairs(player: T): Pair<Boolean, Char?>{
        var highestRank = ' '
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank,
                player.hand.second.rank,
                this.cardsOnTable[0]!!.rank,
                this.cardsOnTable[1]!!.rank,
                this.cardsOnTable[2]!!.rank,
                this.cardsOnTable[3]!!.rank,
                this.cardsOnTable[4]!!.rank)
            list.sort()
        }
        if (player is Computer){

        }
        return Pair(false, null)
    }
}

