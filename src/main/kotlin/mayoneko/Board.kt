package mayoneko

class Board {
    private var cardsMap = mutableMapOf<Int, Int>()
    //key: cardID
    //value: ownerID

    //board's ownerID == -1

    init {
        for (cardID in 0..51) {
            cardsMap[cardID] = -1
        }
    }

    private fun getCardIDs(ownerID: Int): List<Int> {
        val cardIDs = mutableListOf<Int>()
        cardsMap.filter {
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
        cardsMap[cardID] = ownerID
    }

    fun setCardToPlayer(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID)
    }

    fun setCardOnBoard(cardID: Int) {
        setCardOwner(cardID, -1)
    }

    override fun toString(): String {
        var fullBoard = ""
        for ((key, value) in cardsMap) {
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