package org.example.sevens

class Sevens {
    init{
        val users= mutableListOf<User>()
        for (i in 0..2){
            users[i]=User()
        }
        var count=0
        (0..51).toList().random().run {
            users[count%3].cards.add(Card(this%4,this%13))
            count++
        }
    }

    fun main(args: Array<String>) {

    }
}

data class Card(val suit: Int, val num: Int)

enum class Suit(val suitNum:Int) {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3)
}

class User {
    fun playingAlgorithm(){
        //TODO
    }
    var leftPassNum: Int=3
    val cards: MutableList<Card?> = mutableListOf()
}
