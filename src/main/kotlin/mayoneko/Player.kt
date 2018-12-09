package mayoneko

class Player(val id: Int, val algorithm: Algorithm) {

    companion object {
        const val PLAYING = 0
        const val LOSE = 1
        const val WIN = 2
    }

    var cards = listOf<Card>()

    private var state = PLAYING

    private var remainingPassCount: Int = 3

    fun getRemainingPassCount() = remainingPassCount

    fun reduceRemainingPassCount() {
        remainingPassCount--
    }

    fun isPlaying(): Boolean {
        return state == PLAYING
    }

    fun changeToWin() {
        if (this.cards.isEmpty()) {
            this.state = WIN
            return
        } else {
            throw IllegalStateException("this player still has cards")
        }
    }

    fun changeToLose() {
        if (getRemainingPassCount() < 0) {
            this.state = LOSE
            return
        } else {
            throw IllegalStateException("this player can still pass")
        }
    }
}