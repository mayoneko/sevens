package mayoneko


fun main(args: Array<String>) {
    val playersSize = 3
    val players = createPlayers(playersSize)
    val dealer = Dealer(playersSize)
    dealer.setupBoard(players)
    do {
        dealer.playTurn(players[dealer.turnPlayerID])
        dealer.handleTurn(players)
    } while (dealer.isGameEnded(players))
}

fun createPlayers(playersSize: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (playerID in 0 until playersSize) {
        players.add(Player(playerID))
    }
    return players
}