package mayoneko


class Suit {
    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }
}

fun getPlayableCards(cardsOnBoard: List<Card>, playerCards: List<Card>? = null): List<Card> {

    val playableCards = mutableListOf<Card>()
    for (i in 0..3) {
        for (card in searchCards(cardsOnBoard, i, 7)) {
            for (it in card.outerCards()) {
                var outerCardIsValid = true
                var outerCard = it
                while (cardsOnBoard.contains(outerCard)) {
                    if (outerCard.outerCards().isEmpty()) {
                        outerCardIsValid = false
                        break
                    } else {
                        outerCard = outerCard.outerCards().single()
                    }
                }
                if (outerCardIsValid) {
                    playableCards.add(outerCard)
                }
            }
        }
    }

    if (playerCards is List<Card>) {
        val playableCardsForPlayer = mutableListOf<Card>()
        for (card in playerCards) {
            searchCards(playableCards, card.suit, card.number).map { playableCard ->
                playableCardsForPlayer.add(playableCard)
            }
        }
        return playableCardsForPlayer

    } else {
        return playableCards
    }
}