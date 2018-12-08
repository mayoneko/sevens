package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val players = createPlayers(playerNum)
    val board = Board(players)
    board.setupBoard()
}

fun createPlayers(playersSize: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (playerID in 0 until playersSize) {
        players.add(Player(playerID))
    }
    return players
}