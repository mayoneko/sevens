package mayoneko

class Player(val id: Int, private val algorithm: Algorithm) {

    companion object {
        const val PLAYING = 0
        const val LOSE = 1
        const val WIN = 2
    }

    inner class Status {
        val id = this@Player.id
        val state = this@Player.state
        val handNum = this@Player.cards.size
        val lastChosenCard = this@Player.chosenCard
        val remainingPassCount = this@Player.getRemainingPassCount()
    }

    var cards = listOf<Card>()

    private var chosenCard: Card? = null

    private var state = PLAYING

    private var remainingPassCount: Int = 3

    fun getRemainingPassCount() = remainingPassCount

    fun reduceRemainingPassCount() {
        remainingPassCount--
    }

    fun playCard(board: List<Card>, hand: List<Card>, otherPlayersStatus: List<Player.Status>): Card? {
        chosenCard = algorithm.choiceCard(board, hand, getRemainingPassCount(), otherPlayersStatus)
        return chosenCard
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