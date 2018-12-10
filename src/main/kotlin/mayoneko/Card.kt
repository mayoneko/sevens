package mayoneko


class Card {
    val suit: Int
    val number: Int
    val id: Int

    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }

    constructor(_cardID: Int) {
        if (_cardID in (0..51)) {
            id = _cardID
            suit = _cardID / 13
            number = _cardID % 13 + 1
        } else {
            throw IllegalArgumentException("cardID argument must be in 0..51")
        }
    }

    constructor(_suit: Int, _number: Int) {
        if (_suit in (0..3)) {
            suit = _suit
        } else {
            throw IllegalArgumentException("suit argument must be in 0..3")
        }
        if (_number in (1..13)) {
            number = _number
        } else {
            throw IllegalArgumentException("number argument must be in 1..13")
        }
        id = _suit * 13 + (_number - 1)
    }

    override fun toString(): String {
        val suitStr = when (this.suit) {
            0 -> "♥"
            1 -> "♦"
            2 -> "♣"
            3 -> "♠"
            else -> "?"
        }
        val numStr = this.number.toString().padStart(2, '0')

        return "${suitStr}-${numStr}"
    }

}