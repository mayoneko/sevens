package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val board = Board()
    val dealer = Dealer(playerNum)
    val players = createPlayers(playerNum)
    val playerStates = createPlayerStates(playerNum)
    dealer.dealCardsToPlayers(board, players)
    dealer.setStartPlayer(players)
    dealer.setSevenCardsOnBoard(board, players)
    do {
        dealer.playTurn(board, players[dealer.turnPlayerID], playerStates[dealer.turnPlayerID])
        dealer.handleTurn(playerStates)
    } while (!dealer.isGameEnded(playerStates))
}

fun createPlayers(playerNum: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (playerID in 0 until playerNum) {
        players.add(Player(playerID))
    }
    return players
}

fun createPlayerStates(playerNum: Int): List<PlayerState> {
    val playerStates = mutableListOf<PlayerState>()
    for (playerID in 0 until playerNum) {
        playerStates.add(PlayerState(playerID))
    }
    return playerStates
}