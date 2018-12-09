package mayoneko


class Suit {
    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }
}

fun getPlayableCards(cardsOnBoard: List<Card>): List<Card> {
    val playableCards = mutableListOf<Card>()
    for (suit in 0..3) {
        for (num in 6 downTo 1) {
            if (!cardsOnBoard.contains(Card(suit, num))) {
                playableCards.add(Card(suit, num))
                break
            }
        }
        for (num in 8..13) {
            if (!cardsOnBoard.contains(Card(suit, num))) {
                playableCards.add(Card(suit, num))
                break
            }
        }
    }
    return playableCards
}