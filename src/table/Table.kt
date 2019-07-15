package table
import playerInfo.*

const val PAIR_SCORE = 1
const val TWO_PAIRS_SCORE = 2
const val THREE_OF_A_KIND_SCORE = 3
const val STRAIGHT_SCORE = 4
const val FLUSH_SCORE = 5
const val FULL_HOUSE_SCORE = 6
const val FOUR_OF_A_KIND_SCORE = 7
const val STRAIGHT_FLUSH_SCORE = 8

class Table (var cardsOnTable: List<Card?> = listOf(Card(), Card(), Card(), Card(), Card())){
    var totalBet = 0
    init {
         cardsOnTable = listOf(Card(), Card(), Card(), Card(), Card())
    }

    fun openCard(cardNumber: Int){
        println("-------------------------")
        for (i in 0 until cardNumber) {
            print(cardsOnTable[i]?.rank?.getRankName())
            print(cardsOnTable[i]?.suit)
            print(" ")
        }
        println("\n-------------------------")
    }

    fun defineWinner(player: Player, computer: Computer) {
        val playerScore = checkPlayersScore(player).first
        val playerHighestCard = checkPlayersScore(player).second
        val computerScore = checkPlayersScore(computer).first
        val computerHighestCard = checkPlayersScore(computer).second
        if (playerScore == computerScore){
            if (playerHighestCard > computerHighestCard) awardPlayer(player)
            if (playerHighestCard < computerHighestCard) awardComputer(computer)
            else awardBoth(player, computer)
        }
        if (playerScore > computerScore) awardPlayer(player)
        if (playerScore < computerScore) awardComputer(computer)
    }

    private fun awardPlayer(player: Player){
        println("You won $totalBet$!")
        player.balance += this.totalBet
    }

    private fun awardComputer(computer: Computer){
        println("You lost $totalBet$. Better luck next time!")
        computer.balance += this.totalBet
    }

    private fun awardBoth(player: Player, computer: Computer){
        println("Draw! Your money ${totalBet/2}$ is back.")
        player.balance += this.totalBet / 2
        computer.balance += this.totalBet / 2
    }

    private fun <T> checkPlayersScore(player: T): Pair<Int, Int>{
        var playerHighestCard: Int
        if (player is Player){
            playerHighestCard = highestCardInHand(player)!!
            if (isStraightFlush(player).first) return Pair(STRAIGHT_FLUSH_SCORE, playerHighestCard)
            if (isFourOfAKind(player).first) return Pair(FOUR_OF_A_KIND_SCORE, playerHighestCard)
            if (isFullHouse(player).first) return Pair(FULL_HOUSE_SCORE, playerHighestCard)
            if (isFlush(player).first) return Pair(FLUSH_SCORE, playerHighestCard)
            if (isStraight(player).first) return Pair(STRAIGHT_SCORE, playerHighestCard)
            if (isThreeOfAKind(player).first) return Pair(THREE_OF_A_KIND_SCORE, playerHighestCard)
            if (isTwoPairs(player).first) return Pair(TWO_PAIRS_SCORE, playerHighestCard)
            if (isPair(player).first) return Pair(PAIR_SCORE, playerHighestCard)
        }
        if (player is Computer){
            playerHighestCard = highestCardInHand(player)!!
            if (isStraightFlush(player).first) return Pair(STRAIGHT_FLUSH_SCORE, playerHighestCard)
            if (isFourOfAKind(player).first) return Pair(FOUR_OF_A_KIND_SCORE, playerHighestCard)
            if (isFullHouse(player).first) return Pair(FULL_HOUSE_SCORE, playerHighestCard)
            if (isFlush(player).first) return Pair(FLUSH_SCORE, playerHighestCard)
            if (isStraight(player).first) return Pair(STRAIGHT_SCORE, playerHighestCard)
            if (isThreeOfAKind(player).first) return Pair(THREE_OF_A_KIND_SCORE, playerHighestCard)
            if (isTwoPairs(player).first) return Pair(TWO_PAIRS_SCORE, playerHighestCard)
            if (isPair(player).first) return Pair(PAIR_SCORE, playerHighestCard)
        }
        return Pair(0, 0)
    }

    private fun <T> isPair(player: T): Pair<Boolean, Int?>{
        if (player is Player){
            if (player.hand.first.rank == player.hand.second.rank) return Pair(true, player.hand.first.rank.getCardRank())
            for (i in 0 until this.cardsOnTable.size){
                if (player.hand.first.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.first.rank.getCardRank())
                if (player.hand.second.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.second.rank.getCardRank())
            }
        }
        if (player is Computer){
            if (player.hand.first.rank == player.hand.second.rank) return Pair(true, player.hand.first.rank.getCardRank())
            for (i in 0 until this.cardsOnTable.size){
                if (player.hand.first.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.first.rank.getCardRank())
                if (player.hand.second.rank == this.cardsOnTable[i]?.rank) return Pair(true, player.hand.second.rank.getCardRank())
            }
        }
        return Pair(false, null)
    }

    private fun <T> isThreeOfAKind(player: T): Pair<Boolean,Int?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 3) return Pair(true, list[i])
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 3) return Pair(true, list[i])
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        return Pair(false, null)
    }

    private fun <T> isFourOfAKind(player: T): Pair<Boolean,Int?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 4) return Pair(true, this.cardsOnTable[i]?.rank?.getCardRank())
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var result = 1
            for (i in 0 until list.size - 1){
                if (result == 4) return Pair(true, this.cardsOnTable[i]?.rank?.getCardRank())
                if (list[i] == list[i + 1]) result++
                else result = 1
            }
        }
        return Pair(false, null)
    }

    private fun <T> highestCardInHand(player: T): Int?{
        var highestCard = 0
        if (player is Player){
            for (i in 2..14){
                if (player.hand.first.rank.getCardRank() == i) highestCard = i
                if (player.hand.second.rank.getCardRank() == i && highestCard < i) highestCard = i
            }
        }
        if (player is Computer){
            for (i in 2..14){
                if (player.hand.first.rank.getCardRank() == i) highestCard = i
                if (player.hand.second.rank.getCardRank() == i && highestCard < i) highestCard = i
            }
        }
        return highestCard
    }

    private fun <T> isTwoPairs(player: T): Pair<Boolean, Int?>{
        var firstPairCount = 1
        var firstPos = 0
        var secondPairCount = 1
        var secondPos = 0
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            for (i in 0 until list.size - 1){
                if (list[i] == list[i + 1]) {
                    firstPairCount++
                    firstPos = i
                }
                else {
                    if (secondPairCount < 2) {
                        secondPairCount = firstPairCount
                        secondPos = firstPos
                        firstPairCount = 1
                    } else return Pair(true, if (list[firstPos] > list[secondPos]) list[firstPos] else list[secondPos])
                }
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            for (i in 0 until list.size - 1){
                if (list[i] == list[i + 1]) {
                    firstPairCount++
                    firstPos = i
                }
                else {
                    if (secondPairCount < 2) {
                        secondPairCount = firstPairCount
                        secondPos = firstPos
                        firstPairCount = 1
                    } else if (firstPairCount == 2){
                        return Pair(true, if (list[firstPos] > list[secondPos]) list[firstPos] else list[secondPos])
                    }
                }
            }
        }
        return Pair(false, null)
    }

    private fun <T> isFullHouse(player: T): Pair<Boolean, Int?>{
        var firstPairCount = 1
        var secondPairCount = 1
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            for (i in 0 until list.size - 1){
                if (list[i] == list[i + 1]) {
                    firstPairCount++
                }
                else {
                    if (secondPairCount == 1 && firstPairCount == 3) {
                        secondPairCount = firstPairCount
                        firstPairCount = 1
                    } else if (secondPairCount == 3 && firstPairCount == 2) return Pair(true, null)
                    if (secondPairCount == 1 && firstPairCount == 2) {
                        secondPairCount = firstPairCount
                        firstPairCount = 1
                    } else if (secondPairCount == 2 && firstPairCount == 3) return Pair(true, null)
                }
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            for (i in 0 until list.size - 1){
                if (list[i] == list[i + 1]) {
                    firstPairCount++
                }
                else {
                    if (secondPairCount == 1 && firstPairCount == 3) {
                        secondPairCount = firstPairCount
                        firstPairCount = 1
                    } else if (secondPairCount == 3 && firstPairCount == 2) return Pair(true, null)
                    if (secondPairCount == 1 && firstPairCount == 2) {
                        secondPairCount = firstPairCount
                        firstPairCount = 1
                    } else if (secondPairCount == 2 && firstPairCount == 3) return Pair(true, null)
                }
            }
        }
        return Pair(false, null)
    }

    private fun <T> isStraight(player: T): Pair<Boolean, Int?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var counter = 1
            var highestCard = 0
            for (i in 0 until list.size - 1){
                for (j in i until list.size - 1){
                    if (counter == 5) return Pair(true, highestCard)
                    if (list[j] + 1 == list[j + 1]) {
                        counter++
                        highestCard = j
                    } else counter = 1
                }
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var counter = 1
            var highestCard = 0
            for (i in 0 until list.size - 1){
                for (j in i until list.size - 1){
                    if (counter == 5) return Pair(true, highestCard)
                    if (list[j] + 1 == list[j + 1]) {
                        counter++
                        highestCard = list[j]
                    } else counter = 1
                }
            }
        }
        return Pair(false, null)
    }

    private fun <T> isFlush(player: T): Pair<Boolean, Int?> {
        if (player is Player) {
            val list = mutableListOf(
                player.hand.first.suit,
                player.hand.second.suit,
                this.cardsOnTable[0]!!.suit,
                this.cardsOnTable[1]!!.suit,
                this.cardsOnTable[2]!!.suit,
                this.cardsOnTable[3]!!.suit,
                this.cardsOnTable[4]!!.suit
            )
            list.sort()
            var counter = 1
            for (i in 0 until list.size - 1){
                if (counter == 5) return Pair(true, if (player.hand.first.rank.getCardRank() > player.hand.second.rank.getCardRank()) player.hand.first.rank.getCardRank() else player.hand.second.rank.getCardRank())
                if (list[i] == list[i + 1]) counter++
            }
        }
        if (player is Computer){
            val list = mutableListOf(
                player.hand.first.suit,
                player.hand.second.suit,
                this.cardsOnTable[0]!!.suit,
                this.cardsOnTable[1]!!.suit,
                this.cardsOnTable[2]!!.suit,
                this.cardsOnTable[3]!!.suit,
                this.cardsOnTable[4]!!.suit
            )
            list.sort()
            var counter = 1
            for (i in 0 until list.size - 1){
                if (counter == 5) return Pair(true, if (player.hand.first.rank.getCardRank() > player.hand.second.rank.getCardRank()) player.hand.first.rank.getCardRank() else player.hand.second.rank.getCardRank())
                if (list[i] == list[i + 1]) counter++
            }
        }
        return Pair(false, null)
    }

    private fun <T> isStraightFlush(player: T): Pair<Boolean, Int?>{
        if (player is Player){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var counter = 1
            for (i in 0 until list.size - 1){
                for (j in i until list.size - 1){
                    if (counter == 5) break
                    if (list[j] + 1 == list[j + 1]) {
                        counter++
                    } else counter = 1
                }
            }
            if (counter == 5){
                val suitList = mutableListOf(
                    player.hand.first.suit,
                    player.hand.second.suit,
                    this.cardsOnTable[0]!!.suit,
                    this.cardsOnTable[1]!!.suit,
                    this.cardsOnTable[2]!!.suit,
                    this.cardsOnTable[3]!!.suit,
                    this.cardsOnTable[4]!!.suit
                )
                list.sort()
                var counterSuit = 1
                for (i in 0 until suitList.size - 1){
                    if (counterSuit == 5) return Pair(true, if (player.hand.first.rank.getCardRank() > player.hand.second.rank.getCardRank()) player.hand.first.rank.getCardRank() else player.hand.second.rank.getCardRank())
                    if (suitList[i] == suitList[i + 1]) counterSuit++
                }
            }
        }
        if (player is Computer){
            val list = mutableListOf(player.hand.first.rank.getCardRank(),
                player.hand.second.rank.getCardRank(),
                this.cardsOnTable[0]!!.rank.getCardRank(),
                this.cardsOnTable[1]!!.rank.getCardRank(),
                this.cardsOnTable[2]!!.rank.getCardRank(),
                this.cardsOnTable[3]!!.rank.getCardRank(),
                this.cardsOnTable[4]!!.rank.getCardRank())
            list.sort()
            var counter = 1
            for (i in 0 until list.size - 1){
                for (j in i until list.size - 1){
                    if (counter == 5) break
                    if (list[j] + 1 == list[j + 1]) {
                        counter++
                    } else counter = 1
                }
            }
            if (counter == 5){
                val suitList = mutableListOf(
                    player.hand.first.suit,
                    player.hand.second.suit,
                    this.cardsOnTable[0]!!.suit,
                    this.cardsOnTable[1]!!.suit,
                    this.cardsOnTable[2]!!.suit,
                    this.cardsOnTable[3]!!.suit,
                    this.cardsOnTable[4]!!.suit
                )
                list.sort()
                var counterSuit = 1
                for (i in 0 until suitList.size - 1){
                    if (counterSuit == 5) return Pair(true, if (player.hand.first.rank.getCardRank() > player.hand.second.rank.getCardRank()) player.hand.first.rank.getCardRank() else player.hand.second.rank.getCardRank())
                    if (suitList[i] == suitList[i + 1]) counterSuit++
                }
            }
        }
        return Pair(false, null)
    }
}

