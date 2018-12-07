package mayoneko

import mayoneko.Suit.Companion.CLUBS

class Board(_players: List<Player>) {
    val players = _players

    //PlayerStateがPLAYING以外になったときに格納する
    //keyがrankID、valがplayerID
    val playerRanking= mutableMapOf<Int,Int>()

    val cards = mutableListOf<Card>()

    var turnPlayerID: Int = 0

    fun setupBoard() {
        for ((playerID, cardID) in ((0..51).map { it % 3 }).zip((0..51).shuffled())) {
            dealCard(players[playerID], cardID.toCard())
        }

        for (playerID in 0 until players.size) {
            val player = players[playerID]
            if (player.searchCards(CLUBS, 7).isNotEmpty()) {
                this.turnPlayerID = playerID
            }
        }
    }

    fun playTurn() {
        players[turnPlayerID].playingAlgorithm()
        println(this.cards.toString())
    }

    fun playTurnEnd(){
        if (!isGameEnded()) {
            do {
                turnPlayerID = (turnPlayerID + 1) % 3
            } while (players[turnPlayerID].state == PlayerState.PLAYING)
        }else{
            gameEnding()
        }
    }

    fun dealCard(player: Player, card: Card) {
        player.cards.add(card)
    }

    fun isGameEnded(): Boolean {
        return players.all {
            it.state != PlayerState.PLAYING
        }
    }

    fun gameEnding(){
        println("GameSet\n")
        for(rankID in 0 until players.size){
            println("${rankID}位 : Player${playerRanking[rankID]}")
        }
    }
}

