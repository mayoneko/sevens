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
        val sevenCards = searchCards(player.cards, num = 7)
        sevenCards.forEach { card ->
            play(player, card)
        }
    }

    fun playTurn(player: Player) {
        player.playingAlgorithm(this)
        println(cardsToString(this.getCardsOnBoard()))
    }

    fun handleTurn(players: List<Player>) {
        if (!isGameEnded(players)) {
            do {
                turnPlayerID = (turnPlayerID + 1) % 3
            } while (!players[turnPlayerID].isPlaying())
        } else {
            gameEnding(players)
        }
    }

    fun isGameEnded(players: List<Player>): Boolean {
        return players.all { player ->
            !player.isPlaying()
        }
    }


    //player state manage functions in private

    private fun winPlayer(player: Player) {
        var playerRank = 1
        while (playerRanking.containsKey(playerRank)) {
            playerRank++
        }
        playerRanking[playerRank] = player.playerID
    }

    private fun losePlayer(player: Player) {
        for (card in player.cards) {
            addOneCardOnBoard(card)
        }
        player.cards.removeAll { true }

        var playerRank = playerNum - 1
        while (playerRanking.containsKey(playerRank)) {
            playerRank--
        }
        playerRanking[playerRank] = player.playerID
    }


    private fun gameEnding(players: List<Player>) {
        println("GameSet\n")
        for (rankID in 0 until players.size) {
            println("${rankID}位 : Player${playerRanking[rankID]}")
        }
    }


    //card manage functions for player

    fun play(player: Player, card: Card) {
        player.cards.remove(card)
        this.addOneCardOnBoard(card)
        if (player.hasWinning()) {
            this.winPlayer(player)
        }
    }

    fun pass(player: Player) {
        if (player.hasLosing()) {
            this.losePlayer(player)
        } else {
            player.remainingPassCount--
        }
    }

}

