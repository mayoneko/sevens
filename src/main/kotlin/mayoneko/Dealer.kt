package mayoneko

class Dealer(_playerNum: Int) {

    val playerNum = _playerNum

    //PlayerStateがPLAYING以外になったときに格納する
    //keyがrankID、valがplayerID
    private val playerRanking = mutableMapOf<Int, Int>()

    var turnPlayerID: Int = 0


    //game manage functions

    fun dealCardsToPlayers(board: Board, players: List<Player>) {
        for ((playerID, cardID) in ((0..51).map { it % 3 }).zip((0..51).shuffled())) {
            board.moveCardToPlayer(intToCard(cardID), playerID)
        }
        for (player in players) {
            player.cards = board.getPlayerCards(player.playerID)
        }
    }

    fun setStartPlayer(players: List<Player>) {
        for (player in players) {
            if (searchCards(player.cards, Suit.CLUBS, 7).isNotEmpty()) {
                this.turnPlayerID = player.playerID
            }
        }
    }

    fun setSevenCardsOnBoard(board: Board, players: List<Player>) {
        for (player in players) {
            val sevenCards = searchCards(player.cards, cardNum = 7)
            sevenCards.forEach { card ->
                board.setCardOnBoard(card)
            }
            player.cards = board.getPlayerCards(player.playerID)
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
        player.cards = board.getPlayerCards(player.playerID)
    }

    fun handleState(player: Player, playerState: PlayerState) {
        if (player.cards.isEmpty()) {
            playerState.changeToWin(player)
        }
        if (playerState.getRemainingPassCount() < 0) {
            playerState.changeToLose(player)
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

