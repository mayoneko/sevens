package sevens

class Game(algorithms: List<Algorithm>) {
    private val playerNum = 3
    private val board = Board()
    private val dealer = Dealer(playerNum)
    private val players = createPlayers(algorithms)

    fun run(): Map<Int, Int> {
        var result = mapOf<Int, Int>()
        dealer.dealCardsToPlayers(board, players)
        dealer.setStartPlayer(players)
        dealer.setSevenCardsOnBoard(board, players)
        do {
            val player = players[dealer.turnPlayerID]
            val otherPlayersStatus = players.filter {
                it.id != dealer.turnPlayerID
            }.map { otherPlayer -> otherPlayer.Status() }

//            println("Player${player.id}   RemainingPassCount:${player.getRemainingPassCount()}")
//            println("Cards")
//            println(player.cards.sortedBy { card -> card.id }.toString())

            dealer.playTurn(board, player, otherPlayersStatus)
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

    private fun createPlayers(algorithms: List<Algorithm>): List<Player> {
        return (0 until playerNum).map { Player(it, algorithms[it]) }
    }

}