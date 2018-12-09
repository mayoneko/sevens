package mayoneko

class Board {
    private var ownerMap = mutableMapOf<Int, Int>()
    //key: cardID
    //value: ownerID

    companion object {
        const val BoardID = -1
    }

    init {
        for (cardID in 0..51) {
            ownerMap[cardID] = BoardID
        }
    }

    private fun getCards(ownerID: Int): List<Int> {
        return ownerMap.filter { item ->
            item.value == ownerID
        }.map { item ->
            item.key
        }
    }

    fun getHand(playerID: Int): List<Int> {
        return getCards(playerID)
    }

    fun getBoard(): List<Int> {
        return getCards(BoardID)
    }

    private fun setCardOwner(cardID: Int, ownerID: Int) {
        ownerMap[cardID] = ownerID
    }

    fun setCardToPlayer(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID)
    }

    fun setCardOnBoard(cardID: Int) {
        setCardOwner(cardID, BoardID)
    }

    override fun toString(): String {
        var fullBoard = ""
        for ((key, value) in ownerMap) {
            fullBoard += when (value) {
                -1 -> Card(key).toString()
                else -> "    "
            }
            fullBoard += when {
                key % 13 == 12 -> "\n"
                else -> ","
            }
        }
        return fullBoard
    }
}