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
            else -> throw IllegalStateException("suit property must be in 0..3")
        }
        val numStr = when (this.number) {
            1 -> "A"
            10 -> "0"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            in 2..9 -> this.number.toString()
            else -> throw IllegalStateException("number property must be in 1..13")
        }

        return "{${suitStr}-${numStr}}"
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