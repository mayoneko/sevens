package mayoneko

class Player(_playerID: Int) {
    val cards = mutableListOf<Card>()
    val playerID = _playerID

    companion object {
        const val PLAYING = 0
        const val LOSE = 1
        const val WIN = 2
    }

    private var state = PLAYING
    var remainingPassCount: Int = 3
    fun canPass(): Boolean = remainingPassCount > 0

    fun playingAlgorithm(dealer: Dealer) {
        val playableCards = searchPlayableCards(dealer)
        if (playableCards.isEmpty()) {
            dealer.pass(this)
        } else {
            val cardWillPlay = playableCards.random()
            dealer.play(this, cardWillPlay)
        }
        //override this method and fix yourself!
    }

    fun searchPlayableCards(dealer: Dealer): List<Card> {
        val playableCards = mutableListOf<Card>()
        for (card in this.cards) {
            dealer.searchCards(dealer.getPlayableCards(), card.suitNum, card.num).map { playableCard ->
                playableCards.add(playableCard)
            }
        }
        return playableCards
    }

    fun isPlaying(): Boolean {
        return this.state == PLAYING
    }

    fun hasWinning(): Boolean {
        if (this.cards.isEmpty()) {
            this.state = Player.WIN
            return true
        } else {
            return false
        }
    }

    fun hasLosing(): Boolean {
        if (!this.canPass()) {
            this.state = Player.LOSE
            return true
        } else {
            return false
        }
    }
}