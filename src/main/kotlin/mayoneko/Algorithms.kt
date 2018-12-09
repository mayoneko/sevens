package mayoneko

class Algorithms0 : Algorithm() {
    override fun choiceCard(board: List<Card>, hand: List<Card>): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableHand.random()
            return play(cardWillPlay)
        }
    }
}

class Algorithms1 : Algorithm() {
    override fun choiceCard(board: List<Card>, hand: List<Card>): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableHand.random()
            return play(cardWillPlay)
        }
    }
}

class Algorithms2 : Algorithm() {
    override fun choiceCard(board: List<Card>, hand: List<Card>): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableHand.random()
            return play(cardWillPlay)
        }
    }
}