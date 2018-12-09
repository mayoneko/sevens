package mayoneko

class PlayerState(_playerID: Int) {

    companion object {
        const val PLAYING = 0
        const val LOSE = 1
        const val WIN = 2
    }

    private val playerID = _playerID

    private var state = PLAYING

    private var remainingPassCount: Int = 3

    fun canPass(): Boolean = remainingPassCount > 0

    fun reduceRemainingPassCount() {
        remainingPassCount--
    }

    fun isPlaying(): Boolean {
        return state == PLAYING
    }

    fun changeToWin(player: Player) {
        if (player.playerID != playerID) {
            throw IllegalArgumentException("argument playerID is invalid")
        } else {
            if (player.cards.isEmpty()) {
                this.state = WIN
                return
            } else {
                throw IllegalStateException("this player still has cards")
            }
        }
    }

    fun changeToLose(player: Player) {
        if (player.playerID != playerID) {
            throw IllegalArgumentException("argument playerID is invalid")
        } else {
            if (!canPass()) {
                this.state = LOSE
                return
            } else {
                throw IllegalStateException("this player can still pass")
            }
        }
    }
}