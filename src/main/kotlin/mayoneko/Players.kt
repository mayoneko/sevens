package mayoneko

class Player0 : Player(0) {
    override fun playingAlgorithm(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}

class Player1 : Player(1) {
    override fun playingAlgorithm(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}

class Player2 : Player(2) {
    override fun playingAlgorithm(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}