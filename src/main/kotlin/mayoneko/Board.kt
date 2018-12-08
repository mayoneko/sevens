package mayoneko

class Board {
    private var cardsMap = mutableMapOf<Card, Int>()

    private fun getCards(ownerID: Int): List<Card> {
        val cards = mutableListOf<Card>()
        cardsMap.filter {
            it.value == ownerID
        }.map {
            cards.add(it.key)
        }
        return cards
    }

    fun getPlayerCards(playerID: Int): List<Card> {
        return getCards(playerID)
    }

    fun getBoardCards(): List<Card> {
        return getCards(-1)
    }

    private fun moveCardOwner(card: Card, ownerID: Int) {
        var key: Card? = null
        cardsMap.filter {
            it.key.equals(card)
        }.map {
            key = it.key
        }
        cardsMap[key as Card] = ownerID
    }

    fun moveCardToPlayer(card: Card, playerID: Int) {
        moveCardOwner(card, playerID)
    }

    fun setCardOnBoard(card: Card) {
        moveCardOwner(card, -1)
    }
}