package mayoneko

abstract class Algorithm {
    abstract fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        remainingPassCount: Int,
        otherPlayersStatus: List<Player.Status>
    ): Card?

    fun play(card: Card): Card {
        return card
    }

    fun pass(): Nothing? {
        return null
    }

    fun getPlayableCards(board: List<Card>): List<Card> {
        val playableCards = mutableListOf<Card>()
        for (suit in 0..3) {
            for (num in 6 downTo 1) {
                if (board.any { card -> card.suit == suit && card.number == num }) continue
                playableCards.add(Card(suit, num))
                break
            }
            for (num in 8..13) {
                if (board.any { card -> card.suit == suit && card.number == num }) continue
                playableCards.add(Card(suit, num))
                break
            }
        }
        return playableCards
    }

    fun getPlayableHand(board: List<Card>, hand: List<Card>): List<Card> {
        val playableCards = getPlayableCards(board)
        return playableCards.filter { card ->
            hand.any {
                card.suit == it.suit && card.number == it.number
            }
        }
    }
}