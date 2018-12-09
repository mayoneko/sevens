package mayoneko

class Algorithms0 : Algorithm() {
    override fun choiceCard(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}

class Algorithms1 : Algorithm() {
    override fun choiceCard(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}

class Algorithms2 : Algorithm() {
    override fun choiceCard(playableCards: List<Card>): Card? {
        if (playableCards.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableCards.random()
            return play(cardWillPlay)
        }
    }
}