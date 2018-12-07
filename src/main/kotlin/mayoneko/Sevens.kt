package mayoneko


fun main(args: Array<String>) {
    val board= Board()

    val users = mutableListOf<User>()
    for (i in 0..2) {
        users.add(User())
    }
    var index = 0
    (0..51).toList().shuffled().forEach {
        users[index].cards.add(Card(it % 4, it % 13 + 1))
        index = ++index % 3
    }
    users.map {
        it.viewCards()
    }

}


data class Card(val suitNum: Int, val num: Int)

enum class Suit(val suitNum: Int) {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3)
}

class User : Board() {
    var passCount: Int = 0
    var canPass: Boolean = true

    fun pass() {
        //TODO
    }

    fun play(suitNum: Int, num: Int) {
        //TODO
    }

    fun playingAlgorithm() {
        //TODO
    }
}

open class Board {
    val cards: MutableList<Card> = mutableListOf()

    fun sortCards(): List<Card> {
        return this.cards.sortedWith(compareBy(
            { it.suitNum },
            { it.num }
        ))
    }

    fun viewCard(card:Card):Pair<String,String>{
        return Pair(
            when(card.suitNum){
                0 -> "♥"
                1 -> "♦"
                2 -> "♣"
                3 -> "♠"
                else -> card.suitNum.toString()
            },
            when(card.num){
                1 -> "A"
                10 -> "0"
                11 -> "J"
                12 -> "Q"
                13 -> "K"
                else -> card.num.toString()
            }
        )
    }

    fun viewCards() {
        val sortedCards = sortCards()
        var fullBoard= ""
        var index=0
        for (i in 0..51){
            if(index<sortedCards.size){
                val card = sortedCards[index]

                if(card.suitNum*13+(card.num-1)==i){
                    fullBoard+="{${viewCard(card).first}:${viewCard(card).second}}"
                    index++
                }else{
                    fullBoard+="{-:-}"
                }
            }else{
                fullBoard+="{-:-}"
            }
            if(i%13==12){
                fullBoard+="\n"
            }
        }
        println(fullBoard)
    }
}