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

fun searchCards(cards: List<Card>, suitNum: Int? = null, num: Int? = null): List<Card> {
    return when {
        suitNum is Int && num is Int -> cards.filter { card ->
            card.suitNum == suitNum && card.num == num
        }
        suitNum is Int -> cards.filter { card ->
            card.suitNum == suitNum
        }
        num is Int -> cards.filter { card ->
            card.num == num
        }
        else -> cards
    }
}

fun getPlayableCards(board: Board, player: Player? = null): List<Card> {

    val cardsOnBoard = board.getBoardCards()
    val playableCards = mutableListOf<Card>()
    for (i in 0..3) {
        for (card in searchCards(cardsOnBoard, i, 7)) {
            searchCardsOfOneSideInSuit@
            for (it in card.outerCards()) {
                var outerCard = it
                while (cardsOnBoard.contains(outerCard)) {
                    if (outerCard.outerCards().isNotEmpty()) {
                        break@searchCardsOfOneSideInSuit
                    }
                    outerCard = outerCard.outerCards().single()
                }
                playableCards.add(outerCard)
            }
        }
    }

    if (player is Player) {
        val playableCardsForPlayer = mutableListOf<Card>()
        for (card in player.cards) {
            searchCards(playableCards, card.suitNum, card.num).map { playableCard ->
                playableCardsForPlayer.add(playableCard)
            }
        }
        return playableCardsForPlayer

    } else {
        return playableCards
    }
}