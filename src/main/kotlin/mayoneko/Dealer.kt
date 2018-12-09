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
            player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
        }
    }

    fun setStartPlayer(players: List<Player>) {
        for (player in players) {
            if (player.cards.filter { card -> card.suit == Suit.CLUBS && card.number == 7 }.isNotEmpty()) {
                this.turnPlayerID = player.id
            }
        }
    }

    fun setSevenCardsOnBoard(board: Board, players: List<Player>) {
        for (player in players) {
            val sevenCards = player.cards.filter { card -> card.number == 7 }
            sevenCards.forEach { card ->
                board.setCardOnBoard(card.id)
            }
            player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
        }
    }

    fun playTurn(board: Board, player: Player) {
        val boardCards = board.getBoard().map { cardID -> Card(cardID) }
        val handCards = board.getHand(player.id).map { cardID -> Card(cardID) }
        val maybeCard = player.algorithm.choiceCard(boardCards, handCards)
        if (maybeCard is Card) {
            board.setCardOnBoard(maybeCard.id)
        } else {
            player.reduceRemainingPassCount()
        }
        player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
    }

    fun handleState(board: Board, player: Player) {
        if (player.cards.isEmpty()) {
            player.changeToWin()
            var playerRank = 1
            while (playerRanking.containsKey(playerRank)) {
                playerRank++
            }
            playerRanking[playerRank] = player.id
        }
        if (player.getRemainingPassCount() < 0) {
            player.changeToLose()
            var playerRank = playerNum
            while (playerRanking.containsKey(playerRank)) {
                playerRank--
            }
            playerRanking[playerRank] = player.id
            player.cards.map { card ->
                board.setCardOnBoard(card.id)
            }
        }
    }

    fun handleTurn(players: List<Player>) {
            do {
                turnPlayerID = (turnPlayerID + 1) % 3
            } while (!players[turnPlayerID].isPlaying())
    }

    fun isGameEnded(players: List<Player>): Boolean {
        return players.all { player ->
            !player.isPlaying()
        }
    }

    fun gameEnding(): Map<Int, Int> {
//        println("GameSet\n")
//        for (rankID in 1..playerNum) {
//            println("${rankID}位 : Player${playerRanking[rankID]}")
//        }
        return playerRanking
    }
}

