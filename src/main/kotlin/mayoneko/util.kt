package mayoneko


class Suit {
    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }
}

fun cardToInt(card: Card): Int {
    return card.suitNum * 13 + (card.num - 1)
}

fun intToCard(cardID: Int): Card {
    return Card(cardID % 4, cardID % 13 + 1)
}

fun cardsToString(cards: List<Card>): String {
    var fullBoard = ""
    var index = 0
    val sortedCards = sortCards(cards)
    for (i in 0..51) {
        val card = sortedCards[index]
        if (i == cardToInt(card)) {
            fullBoard += card.toString()
            if (index < cards.size - 1) {
                index++
            }
        } else {
            fullBoard += "{-:-}"
        }
        if (i % 13 == 12) {
            fullBoard += "\n"
        }
    }
    return fullBoard
}

fun sortCards(cards: List<Card>): List<Card> {
    return cards.sortedBy { card ->
        cardToInt(card)
    }
}