package mayoneko

class Player(_playerID: Int) {
    var cards = listOf<Card>()
    val playerID = _playerID

    fun playingAlgorithm(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
        //override this method and fix yourself!
    }

    fun play(card: Card): Card {
        return card
    }

    fun pass(): Nothing? {
        return null
    }
}