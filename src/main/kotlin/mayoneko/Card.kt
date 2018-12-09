package mayoneko


data class Card(val suit: Int, val number: Int) {

    init {
        if (suit !in (0..3)) {
            throw IllegalArgumentException("suit argument must be in 0..3")
        }
        if (number !in (1..13)) {
            throw IllegalArgumentException("number argument must be in 1..13")
        }
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