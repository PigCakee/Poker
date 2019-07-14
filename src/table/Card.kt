package table

class Card {
    val rank: Char = when ((2..14).random()){
        2 -> '2'; 3 -> '3'; 4 -> '4'; 5 -> '5'
        6 -> '6'; 7 -> '7'; 8 -> '8'; 9 -> '9'; 10 -> '1'
        11 -> 'J'; 12 -> 'Q'; 13 -> 'K'; 14 -> 'A'
        else -> '2'
    }
    val suit: Char = when ((1..4).random()){
        1 -> 'S'
        2 -> 'H'
        3 -> 'D'
        4 -> 'C'
        else -> throw Exception ("Error with generating suit")
    }
}

fun isValid(){
}