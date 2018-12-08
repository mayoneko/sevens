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
        val cardsCanPlay = searchCardsCanPlay(dealer)
        if (cardsCanPlay.isEmpty()) {
            dealer.pass(this)
        } else {
            val cardWillPlay = cardsCanPlay.random()
            dealer.play(this, cardWillPlay)
        }
        //override this method and fix yourself!
    }

    fun searchCardsCanPlay(dealer: Dealer): List<Card> {
        val cardsCanPlay = mutableListOf<Card>()
        for (card in this.cards) {
            dealer.searchCards(dealer.getCardsCanPlay(), card.suitNum, card.num).map { cardCanPlay ->
                cardsCanPlay.add(cardCanPlay)
            }
        }
        return cardsCanPlay
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