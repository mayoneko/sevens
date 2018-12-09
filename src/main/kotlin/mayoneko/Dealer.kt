package mayoneko

class Dealer(_playerNum: Int) {

    val playerNum = _playerNum

    //PlayerStateがPLAYING以外になったときに格納する
    //keyがrankID、valがplayerID
    private val playerRanking = mutableMapOf<Int, Int>()

    var turnPlayerID: Int = 0


    //game manage functions

    fun dealCardsToPlayers(board: Board, players: List<Player>) {
        for ((cardID, playerID) in (0..51).shuffled().zip((0..51).map { it % 3 })) {
            board.setCardToPlayer(cardID, playerID)
        }
        for (player in players) {
            player.cards = board.getPlayerCardIDs(player.playerID).map { cardID -> Card(cardID) }
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
            val sevenCards = searchCards(player.cards, number = 7)
            sevenCards.forEach { card ->
                board.setCardOnBoard(card.id)
            }
            player.cards = board.getPlayerCardIDs(player.playerID).map { cardID -> Card(cardID) }
        }
    }

    fun playTurn(board: Board, player: Player, playerState: PlayerState) {
        val playableCards = getPlayableCards(board.getBoardCardIDs().map { cardID -> Card(cardID) }, player.cards)
        val maybeCard = player.playingAlgorithm(playableCards)
        if (maybeCard is Card) {
            board.setCardOnBoard(maybeCard.id)
        } else {
            playerState.reduceRemainingPassCount()
        }
        player.cards = board.getPlayerCardIDs(player.playerID).map { cardID -> Card(cardID) }
    }

    fun handleState(board: Board, player: Player, playerState: PlayerState) {
        if (player.cards.isEmpty()) {
            playerState.changeToWin(player)
            var playerRank = 1
            while (playerRanking.containsKey(playerRank)) {
                playerRank++
            }
            playerRanking[playerRank] = player.playerID
        }
        if (playerState.getRemainingPassCount() < 0) {
            playerState.changeToLose(player)
            var playerRank = playerNum
            while (playerRanking.containsKey(playerRank)) {
                playerRank--
            }
            playerRanking[playerRank] = player.playerID
            player.cards.map { card ->
                board.setCardOnBoard(card.id)
            }
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
        for (rankID in 1..playerNum) {
            println("${rankID}位 : Player${playerRanking[rankID]}")
        }
    }
}

