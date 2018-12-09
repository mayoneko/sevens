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

    fun changeToWin(player: Player): Boolean {
        //TODO
        return false
    }

    fun changeToLose(player: Player): Boolean {
        //TODO
        return false
    }
}