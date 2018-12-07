package mayoneko


fun main(args: Array<String>) {
    val cards = Cards()
    val board = Board(cards)
    val users = mutableListOf<User>()
    for (i in 0..2) {
        users.add(User(cards,i+1))
    }
    board.viewCards()
    users.map {
        it.viewCards()
    }
}


data class Card(val suitNum: Int, val num: Int, val ownerID: Int)

enum class Suit(val suitNum: Int) {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3)
}

enum class Owner(val ownerID: Int) {
    BOARD(0), USER1(1), USER2(2), USER3(3)
}

class Cards {
    val cards: MutableList<Card>

    init {
        val ownerIDArray = (0..51).map { it % 3 + 1 }.shuffled()
        cards = mutableListOf()
        for (i in 0..51) {
            cards.add(Card(i % 4, i % 13 + 1, ownerIDArray[i]))
        }
    }

}

class User(_cards: Cards, _ownerID: Int) : Board(_cards) {
    override val cards: Cards = _cards
    override val ownerID = _ownerID

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

open class Board(_cards: Cards) {
    open val cards: Cards = _cards
    open val ownerID = 0

    private fun viewCard(card: Card): Pair<String, String> {
        return Pair(
            when (card.suitNum) {
                0 -> "♥"
                1 -> "♦"
                2 -> "♣"
                3 -> "♠"
                else -> card.suitNum.toString()
            },
            when (card.num) {
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
        var fullBoard = ""
        for (i in 0..51) {
            val card = this.cards.cards[i]
            fullBoard += if (card.ownerID == this.ownerID) {
                "{${viewCard(card).first}:${viewCard(card).second}}"
            } else {
                "{-:-}"
            }
            if (i % 13 == 12) {
                fullBoard += "\n"
            }
        }
        println(fullBoard)
    }
}