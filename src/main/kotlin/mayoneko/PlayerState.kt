package mayoneko

class PlayerState(_playerNum: Int) {
    private val playerNum = _playerNum

    private val playerStates = mutableMapOf<Int, Int>()

    init {
        for (playerID in 0 until playerNum) {
            playerStates[playerID] = Player.PLAYING
        }
    }

    private fun getPlayerState(playerID: Int): Int? {
        return playerStates[playerID]
    }

    private fun setPlayerState(playerState: Int, playerID: Int) {
        playerStates[playerID] = playerState
    }

    fun changeToWin(player: Player) {
        if (player.cards.isEmpty()) {
            playerStates[player.playerID] = Player.WIN
            return
        } else {
            throw IllegalStateException("this player still has cards")
        }
    }

    fun changeToLose(player: Player) {
        if (!player.canPass()) {
            playerStates[player.playerID] = Player.LOSE
            return
        } else {
            throw IllegalStateException("this player can still pass")
        }
    }
}