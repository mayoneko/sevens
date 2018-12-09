package mayoneko


class Card {
    val suit: Int
    val number: Int
    val id: Int

    constructor(_cardID: Int) {
        suit = _cardID % 4
        number = _cardID % 13 + 1
        id = _cardID
    }

    constructor(_suit: Int, _number: Int) {
        if (_suit in (0..3)) {
            suit = _suit
        } else {
            throw IllegalArgumentException("suit argument must be in 0..3")
        }
        if (_number !in (1..13)) {
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

    fun outerCards(): List<Card> {
        return when (this.number) {
            in 2..6 -> listOf(Card(this.suit, this.number - 1))
            in 8..12 -> listOf(Card(this.suit, this.number + 1))
            7 -> listOf(Card(this.suit, this.number - 1), Card(this.suit, this.number + 1))
            else -> listOf()
        }
    }

    fun innerCards(): List<Card> {
        return when (this.number) {
            in 1..6 -> listOf(Card(this.suit, this.number + 1))
            in 8..13 -> listOf(Card(this.suit, this.number - 1))
            else -> listOf()
        }
    }
}