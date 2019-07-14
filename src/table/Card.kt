package table

class Card {

    var rank: Rank = when ((2..14).random()) {
        2 -> Rank.TWO
        3 -> Rank.THREE
        4 -> Rank.FOUR
        5 -> Rank.FIVE
        6 -> Rank.SIX
        7 -> Rank.SEVEN
        8 -> Rank.EIGHT
        9 -> Rank.NINE
        10 -> Rank.TEN
        11 -> Rank.JACK
        12 -> Rank.QUEEN
        13 -> Rank.KING
        14 -> Rank.ACE
        else -> throw Exception("Error in rank system")
    }

    val suit: Char = when ((1..4).random()){
        1 -> 'S'
        2 -> 'H'
        3 -> 'D'
        4 -> 'C'
        else -> throw Exception ("Error with generating suit")
    }
    enum class Rank (var rank: Int){
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
        fun getCardRank() = rank
        fun getRankName() = when (rank) {
            2 -> '2'
            3 -> '3'
            4 -> '4'
            5 -> '5'
            6 -> '6'
            7 -> '7'
            8 -> '8'
            9 -> '9'
            10 -> "10"
            11 -> 'J'
            12 -> 'Q'
            13 -> 'K'
            14 -> 'A'
            else -> throw Exception("Error in rank name system")
        }
    }

}



fun isValid(){

}