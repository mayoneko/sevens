package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val board = Board()
    val dealer = Dealer(playerNum)
    val players = createPlayers()
    dealer.dealCardsToPlayers(board, players)
    dealer.setStartPlayer(players)
    dealer.setSevenCardsOnBoard(board, players)
    do {
        val player = players[dealer.turnPlayerID]

        println("Player${player.id}   RemainingPassCount:${player.getRemainingPassCount()}")
        println("Cards")
        println(player.cards.sortedBy { card -> card.id }.toString())

        dealer.playTurn(board, player)
        dealer.handleState(board, player)

        println("now board")
        println(board.toString())

        dealer.handleTurn(players)
    } while (!dealer.isGameEnded(players))
}

fun createPlayers(): List<Player> {
    return listOf(Player(0, Algorithms0()), Player(1, Algorithms1()), Player(2, Algorithms2()))
}
