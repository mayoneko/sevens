package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val board = Board()
    val dealer = Dealer(playerNum)
    val players = createPlayers()
    val playerStates = createPlayerStates(playerNum)
    dealer.dealCardsToPlayers(board, players)
    dealer.setStartPlayer(players)
    dealer.setSevenCardsOnBoard(board, players)
    do {
        val player = players[dealer.turnPlayerID]
        val playerState = playerStates[dealer.turnPlayerID]

        println("Player${player.playerID}   RemainingPassCount:${playerState.getRemainingPassCount()}")
        println("Cards")
        println(cardsToString(player.cards, false))

        dealer.playTurn(board, player, playerState)
        dealer.handleState(board, player, playerState)

        println("now board")
        println(cardsToString(board.getBoardCards()))

        dealer.handleTurn(playerStates)
    } while (!dealer.isGameEnded(playerStates))
}

fun createPlayers(): List<Player> {
    val players = mutableListOf<Player>()
    players.add(Player0())
    players.add(Player1())
    players.add(Player2())
    return players
}

fun createPlayerStates(playerNum: Int): List<PlayerState> {
    val playerStates = mutableListOf<PlayerState>()
    for (playerID in 0 until playerNum) {
        playerStates.add(PlayerState(playerID))
    }
    return playerStates
}