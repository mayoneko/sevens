package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val players = createPlayers(playerNum)
    val dealer = Dealer(playerNum)
    dealer.setupBoard(players)
    do {
        dealer.playTurn(players[dealer.turnPlayerID])
        dealer.handleTurn(players)
    } while (!dealer.isGameEnded(players))
}

fun createPlayers(playerNum: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (playerID in 0 until playerNum) {
        players.add(Player(playerID))
    }
    return players
}