package mayoneko

class Board {
    private var ownerMap = mutableMapOf<Int, Int>()
    //key: cardID
    //value: ownerID

    //board's ownerID == -1

    init {
        for (cardID in 0..51) {
            ownerMap[cardID] = -1
        }
    }

    private fun getCardIDs(ownerID: Int): List<Int> {
        val cardIDs = mutableListOf<Int>()
        ownerMap.filter {
            it.value == ownerID
        }.map {
            cardIDs.add(it.key)
        }
        return cardIDs
    }

    fun getPlayerCardIDs(playerID: Int): List<Int> {
        return getCardIDs(playerID)
    }

    fun getBoardCardIDs(): List<Int> {
        return getCardIDs(-1)
    }

    private fun setCardOwner(cardID: Int, ownerID: Int) {
        ownerMap[cardID] = ownerID
    }

    fun setCardToPlayer(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID)
    }

    fun setCardOnBoard(cardID: Int) {
        setCardOwner(cardID, -1)
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