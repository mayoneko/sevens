package mayoneko

class Game {

    fun run(): Map<Int, Int> {
        val playerNum = 3
        val board = Board()
        val dealer = Dealer(playerNum)
        val players = createPlayers()
        var result = mapOf<Int, Int>()
        dealer.dealCardsToPlayers(board, players)
        dealer.setStartPlayer(players)
        dealer.setSevenCardsOnBoard(board, players)
        do {
            val player = players[dealer.turnPlayerID]

//            println("Player${player.id}   RemainingPassCount:${player.getRemainingPassCount()}")
//            println("Cards")
//            println(player.cards.sortedBy { card -> card.id }.toString())

            dealer.playTurn(board, player)
            dealer.handleState(board, player)

//            println("now board")
//            println(board.toString())

            if (!dealer.isGameEnded(players)) {
                dealer.handleTurn(players)
            } else {
                result = dealer.getPlayerRanking()
            }
        } while (!dealer.isGameEnded(players))

        return result
    }

    fun createPlayers(): List<Player> {
        return listOf(Player(0, RandomAlgorithm()), Player(1, KidsAlgorithm()), Player(2, MayonekoAlgorithm()))
    }

}