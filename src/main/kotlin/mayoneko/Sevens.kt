package mayoneko


fun main(args: Array<String>) {

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
        println(it.cards)
    }

}


data class Card(val suitNum: Int, val num: Int)

enum class Suit(val suitNum: Int) {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3)
}

class User {
    val cards: MutableList<Card?> = mutableListOf()

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
