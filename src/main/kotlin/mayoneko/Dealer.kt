package mayoneko

class Dealer(_playersSize: Int) {

    val playersSize = _playersSize

    //PlayerStateがPLAYING以外になったときに格納する
    //keyがrankID、valがplayerID
    private val playerRanking = mutableMapOf<Int, Int>()

    private val cardsOnBoard = mutableListOf<Card>()

    var turnPlayerID: Int = 0


    //game manage functions

    fun setupBoard(players: List<Player>) {
        for ((playerID, cardID) in ((0..51).map { it % 3 }).zip((0..51).shuffled())) {
            dealOneCard(players[playerID], intToCard(cardID))
        }

        for (playerID in 0 until players.size) {
            val player = players[playerID]
            if (searchCards(player.cards, Suit.CLUBS, 7).isNotEmpty()) {
                this.turnPlayerID = playerID
            }
            val sevenCards = searchCards(player.cards, num = 7)
            sevenCards.forEach { card ->
                play(player, card)
            }
        }
    }

    fun playTurn(player: Player) {
        player.playingAlgorithm(this)
        println(cardsToString(this.showCardsOnBoard()))
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

        var playerRank = playersSize - 1
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

    fun searchCards(cards: List<Card>, suitNum: Int? = null, num: Int? = null): List<Card> {
        return when {
            suitNum is Int && num is Int -> cards.filter { card ->
                card.suitNum == suitNum && card.num == num
            }
            suitNum is Int -> cards.filter { card ->
                card.suitNum == suitNum
            }
            num is Int -> cards.filter { card ->
                card.num == num
            }
            else -> cards
        }
    }

    fun showCardsOnBoard(): List<Card> = this.cardsOnBoard

    fun showCardsCanPlay(): List<Card> {
        val cardsOnBoard = showCardsOnBoard()
        val cardsCanPlay = mutableListOf<Card>()
        for (i in 0..3) {
            for (card in searchCards(cardsOnBoard, i, 7)) {
                searchCardsOfOneSideInSuit@
                for (it in card.outerCards()) {
                    var outerCard = it
                    while (cardsOnBoard.contains(outerCard)) {
                        if (outerCard.outerCards().isNotEmpty()) {
                            break@searchCardsOfOneSideInSuit
                        }
                        outerCard = outerCard.outerCards().single()
                    }
                    cardsCanPlay.add(outerCard)
                }
            }
        }
        return cardsCanPlay
    }


    //card manage functions in private

    private fun dealOneCard(player: Player, card: Card) {
        player.cards.add(card)
    }

    private fun addOneCardOnBoard(card: Card) {
        this.cardsOnBoard.add(card)
    }


}

