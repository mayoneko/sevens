package mayoneko


fun main(args: Array<String>) {
    val playerNum = 3
    val players = createPlayers(playerNum)
    val board = Board(players)
    board.setupBoard()
}

fun createPlayers(playerNum: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (playerID in 0 until playerNum) {
        players.add(Player())
    }
    return players
}