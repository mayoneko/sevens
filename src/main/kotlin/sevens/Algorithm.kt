package sevens

abstract class Algorithm {
    abstract fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        remainingPassCount: Int,
        otherPlayersStatus: List<Player.Status>
    ): Card?

    object Utils {
        @JvmStatic
        fun play(card: Card): Card {
            return card
        }

        @JvmStatic
        fun pass(): Card? {
            return null
        }

        @JvmStatic
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

        @JvmStatic
        fun getPlayableHand(board: List<Card>, hand: List<Card>): List<Card> {
            val playableCards = getPlayableCards(board)
            return playableCards.filter { card ->
                hand.any {
                    card.suit == it.suit && card.number == it.number
                }
            }
        }
    }
}