package mayoneko

abstract class Algorithm {
    abstract fun choiceCard(board: List<Card>, hand: List<Card>): Card?

    fun play(card: Card): Card {
        return card
    }

    fun pass(): Nothing? {
        return null
    }
}