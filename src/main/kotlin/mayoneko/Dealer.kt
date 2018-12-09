package mayoneko

class Dealer(_playerNum: Int) {

    val playerNum = _playerNum

    //PlayerStateがPLAYING以外になったときに格納する
    //keyがrankID、valがplayerID
    private val playerRanking = mutableMapOf<Int, Int>()

    var turnPlayerID: Int = 0


    //game manage functions

    fun dealCardsToPlayers(board: Board) {
        for ((playerID, cardID) in ((0..51).map { it % 3 }).zip((0..51).shuffled())) {
            board.moveCardToPlayer(intToCard(cardID), playerID)
        }
    }

    fun setStartPlayer(players: List<Player>) {
        for (playerID in 0 until playerNum) {
            val player = players[playerID]
            if (searchCards(player.cards, Suit.CLUBS, 7).isNotEmpty()) {
                this.turnPlayerID = playerID
            }
        }
    }

    fun setSevenCardsOnBoard() { //TODO: move to Player Class
        val sevenCards = searchCards(player.cards, cardNum = 7)
        sevenCards.forEach { card ->
            play(player, card)
        }
    }

    fun playTurn(board: Board, player: Player, playerState: PlayerState) {
        val playableCards = getPlayableCards(board.getBoardCards(), player.cards)
        val maybeCard = player.playingAlgorithm(playableCards)
        if (maybeCard is Card) {
            board.setCardOnBoard(maybeCard)
        } else {
            playerState.reduceRemainingPassCount()
        }
    }

    fun handleTurn(playerStates: List<PlayerState>) {
        if (!isGameEnded(playerStates)) {
            do {
                turnPlayerID = (turnPlayerID + 1) % 3
            } while (!playerStates[turnPlayerID].isPlaying())
        } else {
            gameEnding()
        }
    }

    fun isGameEnded(playerStates: List<PlayerState>): Boolean {
        return playerStates.all { playerState ->
            !playerState.isPlaying()
        }
    }

    private fun gameEnding() {
        println("GameSet\n")
        for (rankID in 0 until playerNum) {
            println("${rankID}位 : Player${playerRanking[rankID]}")
        }
    }
}

