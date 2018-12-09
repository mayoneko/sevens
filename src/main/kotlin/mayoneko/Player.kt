package mayoneko

abstract class Player(_playerID: Int) {
    var cards = listOf<Card>()
    val playerID = _playerID

    abstract fun playingAlgorithm(playableCards: List<Card>): Card?

    fun play(card: Card): Card {
        return card
    }

    fun pass(): Nothing? {
        return null
    }
}